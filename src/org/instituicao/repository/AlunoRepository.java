package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;

import java.util.HashSet;
import java.util.List;
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
        return alunoEntities.stream()
                .filter(alunoEntity -> alunoEntity.getEmailAcademico().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<AlunoEntity> getAlunoByMatricula(int matriculaAluno) {
        return alunoEntities.stream()
                .filter(alunoEntity -> alunoEntity.getMatricula() == matriculaAluno)
                .findFirst();
    }

    public List<AlunoEntity> getAlunosByInstituicao(int instituicao) {
        return alunoEntities.stream()
                .filter(alunoEntity -> alunoEntity.getInstituicaoEntity().getId() == instituicao)
                .toList();
    }

}
