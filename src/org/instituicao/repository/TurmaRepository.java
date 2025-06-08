package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;
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

    public List<TurmaEntity> getTurmasByProfessor(ProfessorEntity professorEntity) {
        return turmaEntities.stream().filter(turmaEntity -> turmaEntity.getProfessores().contains(professorEntity)).toList();
    }
}
