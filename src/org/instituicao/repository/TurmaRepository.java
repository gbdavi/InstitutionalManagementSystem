package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.CursoEntity;
import org.instituicao.entity.ProfessorEntity;
import org.instituicao.entity.TurmaEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class TurmaRepository {
    private static HashSet<TurmaEntity> turmaEntities = new HashSet<>();

    /**
     * Adiciona turma ao repositório local.
     */
    public boolean adicionarTurma(TurmaEntity turmaEntity) {
        return turmaEntity != null && turmaEntities.add(turmaEntity);
    }

    /**
     * Remove turma do repositório local.
     */
    public boolean removerTurma(TurmaEntity turmaEntity) {
        return turmaEntities.remove(turmaEntity);
    }

    /**
     * Busca turma no repositório local através do id.
     */
    public Optional<TurmaEntity> getTurmaById(int idTurma) {
        return turmaEntities.stream()
                .filter(turmaEntity -> turmaEntity.getId() == idTurma)
                .findFirst();
    }

    /**
     * Busca turmas que o aluno está adicionado no repositório local através da matrícula do aluno.
     */
    public List<TurmaEntity> getTurmasByAluno(int matriculaAluno) {
        return turmaEntities.stream()
                .filter(turmaEntity -> turmaEntity.getAlunos().stream().anyMatch(alunoEntity -> alunoEntity.getMatricula() == matriculaAluno))
                .toList();
    }

    /**
     * Busca turmas que um aluno pode ser adicionado no repositório local através da entidade do aluno.
     */
    public List<TurmaEntity> getTurmasDisponiveisByAluno(AlunoEntity alunoEntity) {
        return turmaEntities.stream()
                .filter(turmaEntity -> !alunoEntity.getTurmas().contains(turmaEntity) && alunoEntity.getCursos().stream().anyMatch(curso -> curso.getDisciplinas().contains(turmaEntity.getDisciplina())))
                .toList();
    }

    /**
     * Busca turmas cadastradas na instituição no repositório local através do id da instituição.
     */
    public List<TurmaEntity> getTurmasByInstituicao(int idInstituicao) {
        return turmaEntities.stream()
                .filter(turmaEntity -> turmaEntity.getDisciplina().getInstituicaoEntity().getId() == idInstituicao)
                .toList();
    }

    /**
     * Busca turmas que o professor foi adicionado no repositório local através da matrícula do professor.
     */
    public List<TurmaEntity> getTurmasByProfessor(int matriculaProfessor) {
        return turmaEntities.stream()
                .filter(turmaEntity -> turmaEntity.getProfessores().stream().anyMatch(professorEntity -> professorEntity.getMatricula() == matriculaProfessor))
                .toList();
    }
}
