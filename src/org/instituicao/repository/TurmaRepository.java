package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.ProfessorEntity;
import org.instituicao.entity.TurmaEntity;

import java.util.HashSet;
import java.util.List;

public class TurmaRepository {
    private static HashSet<TurmaEntity> turmaEntities = new HashSet<>();

    public boolean adicionarTurma(TurmaEntity turmaEntity) {
        return turmaEntity != null && turmaEntities.add(turmaEntity);
    }

    public boolean removerTurma(TurmaEntity turmaEntity) {
        return turmaEntities.remove(turmaEntity);
    }

    public List<TurmaEntity> getTurmasByAluno(AlunoEntity alunoEntity) {
        return turmaEntities.stream().filter(turmaEntity -> turmaEntity.getAlunos().contains(alunoEntity)).toList();
    }

    public List<TurmaEntity> getTurmasByProfessor(ProfessorEntity professorEntity) {
        return turmaEntities.stream().filter(turmaEntity -> turmaEntity.getProfessores().contains(professorEntity)).toList();
    }
}
