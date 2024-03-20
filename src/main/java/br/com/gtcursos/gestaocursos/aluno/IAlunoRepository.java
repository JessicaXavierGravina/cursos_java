package br.com.gtcursos.gestaocursos.aluno;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlunoRepository extends JpaRepository<AlunoModel, UUID>{

    AlunoModel findByUsername(String username);
    
}
