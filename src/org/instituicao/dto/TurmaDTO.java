package org.instituicao.dto;

import org.instituicao.entity.TurmaEntity;
import org.instituicao.type.StatusTurma;

public class TurmaDTO {
    private final int id;
    private final DisciplinaDTO disciplina;
    private final StatusTurma status;

    public TurmaDTO(int id, DisciplinaDTO disciplina, StatusTurma status) {
        this.id = id;
        this.disciplina = disciplina;
        this.status = status;
    }

    public TurmaDTO(TurmaEntity turma) {
        this.id = turma.getId();
        this.disciplina = new DisciplinaDTO(turma.getDisciplina());
        this.status = turma.getStatus();
    }

    public int getId() {
        return id;
    }

    public DisciplinaDTO getDisciplina() {
        return disciplina;
    }

    public StatusTurma getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return id + " - " + disciplina.getNome();
    }
}
