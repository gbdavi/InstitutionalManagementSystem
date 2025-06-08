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

    public boolean adicionarTurma(TurmaEntity turmaEntity) {
        return turmaEntity != null && turmaEntities.add(turmaEntity);
    }

    public boolean removerTurma(TurmaEntity turmaEntity) {
        return turmaEntities.remove(turmaEntity);
    }

    public Optional<TurmaEntity> getTurmaById(int idTurma) {
        return turmaEntities.stream().filter(turmaEntity -> turmaEntity.getId() == idTurma).findFirst();
    }

    public List<TurmaEntity> getTurmasByAluno(int matricula) {
        return turmaEntities.stream().filter(turmaEntity -> turmaEntity.getAlunos().stream().anyMatch(alunoEntity -> alunoEntity.getMatricula() == matricula)).toList();
    }

    public List<TurmaEntity> getTurmasDisponiveisByAluno(AlunoEntity alunoEntity) {
        return turmaEntities.stream()
                .filter(turmaEntity -> !alunoEntity.getTurmas().contains(turmaEntity) && alunoEntity.getCursos().stream().anyMatch(curso -> curso.getDisciplinas().contains(turmaEntity.getDisciplina())))
                .toList();
    }

    public List<TurmaEntity> getTurmasByInstituicao(int idInstituicao) {
        return turmaEntities.stream()
                .filter(turmaEntity -> turmaEntity.getDisciplina().getInstituicaoEntity().getId() == idInstituicao)
                .toList();
    }
}
