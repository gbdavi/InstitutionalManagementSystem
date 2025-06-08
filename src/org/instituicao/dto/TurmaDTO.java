package org.instituicao.dto;

import org.instituicao.entity.TurmaEntity;
import org.instituicao.type.StatusTurma;

public class TurmaDTO {
    private final int id;
    private final int idDisciplina;
    private final StatusTurma status;

    public TurmaDTO(int id, int idDisciplina, StatusTurma status) {
        this.id = id;
        this.idDisciplina = idDisciplina;
        this.status = status;
    }

    public TurmaDTO(TurmaEntity turma) {
        this.id = turma.getId();
        this.idDisciplina = turma.getDisciplina().getId();
        this.status = turma.getStatus();
    }

    public int getId() {
        return id;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public StatusTurma getStatus() {
        return status;
    }
}
