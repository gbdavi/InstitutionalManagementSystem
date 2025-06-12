package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class AlunoRepository {
    private static HashSet<AlunoEntity> alunoEntities = new HashSet<>();

    /**
     * Adiciona aluno ao repositório local.
     */
    public boolean adicionarAluno(AlunoEntity alunoEntity) {
        return alunoEntity != null && alunoEntities.add(alunoEntity);
    }

    /**
     * Remove aluno do repositório local.
     */
    public boolean removerAluno(AlunoEntity alunoEntity) {
        return alunoEntities.remove(alunoEntity);
    }

    /**
     * Busca aluno no repositório local a partir do email acadêmico.
     */
    public Optional<AlunoEntity> getAlunoByEmailAcademico(String email) {
        return alunoEntities.stream()
                .filter(alunoEntity -> alunoEntity.getEmailAcademico().equalsIgnoreCase(email))
                .findFirst();
    }

    /**
     * Busca aluno no repositório local a partir da matrícula.
     */
    public Optional<AlunoEntity> getAlunoByMatricula(int matriculaAluno) {
        return alunoEntities.stream()
                .filter(alunoEntity -> alunoEntity.getMatricula() == matriculaAluno)
                .findFirst();
    }

    /**
     * Busca alunos no repositório local a partir do id da instituição.
     */
    public List<AlunoEntity> getAlunosByInstituicao(int instituicao) {
        return alunoEntities.stream()
                .filter(alunoEntity -> alunoEntity.getInstituicaoEntity().getId() == instituicao)
                .toList();
    }
}
