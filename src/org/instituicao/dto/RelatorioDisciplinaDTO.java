package org.instituicao.dto;

import org.instituicao.type.SituacaoDisciplina;

public class RelatorioDisciplinaDTO {

    private final DisciplinaDTO disciplina;
    private final float nota;
    private final SituacaoDisciplina situacao;

    public RelatorioDisciplinaDTO(DisciplinaDTO disciplina, float nota, SituacaoDisciplina situacao) {
        this.disciplina = disciplina;
        this.nota = nota;
        this.situacao = situacao;
    }

    public DisciplinaDTO getDisciplina() {
        return disciplina;
    }

    public float getNota() {
        return nota;
    }

    public SituacaoDisciplina getSituacao() {
        return situacao;
    }
}
