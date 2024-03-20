package br.com.gtcursos.gestaocursos.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private IAlunoRepository alunoRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody AlunoModel alunoModel) {
        var aluno = this.alunoRepository.findByUsername(alunoModel.getUsername());

        if(aluno != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, alunoModel.getPassword().toCharArray());

        alunoModel.setPassword(passwordHashred);
        
        var alunoCreated = this.alunoRepository.save(alunoModel);
        return ResponseEntity.status(HttpStatus.OK).body(alunoCreated);
    }
}
