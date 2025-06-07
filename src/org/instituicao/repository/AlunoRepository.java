package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;

import java.util.HashSet;
import java.util.Optional;

public class AlunoRepository {
    private static HashSet<AlunoEntity> alunoEntities = new HashSet<>();

    public boolean adicionarAluno(AlunoEntity alunoEntity) {
        return alunoEntity != null && alunoEntities.add(alunoEntity);
    }

    public boolean removerAluno(AlunoEntity alunoEntity) {
        return alunoEntities.remove(alunoEntity);
    }

    public Optional<AlunoEntity> getAlunoByEmailAcademico(String email) {
        return alunoEntities.stream().filter(aluno -> aluno.getEmailAcademico().equalsIgnoreCase(email)).findFirst();
    }

}
