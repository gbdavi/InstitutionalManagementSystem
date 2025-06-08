package org.instituicao.dto;

import org.instituicao.type.SituacaoDisciplina;

public class RelatorioDisciplinaDTO {

    private final DisciplinaDTO disciplina;
    private final Float media;
    private final SituacaoDisciplina situacao;

    public RelatorioDisciplinaDTO(DisciplinaDTO disciplina, Float media, SituacaoDisciplina situacao) {
        this.disciplina = disciplina;
        this.media = media;
        this.situacao = situacao;
    }

    public DisciplinaDTO getDisciplina() {
        return disciplina;
    }

    public Float getMedia() {
        return media;
    }

    public SituacaoDisciplina getSituacao() {
        return situacao;
    }
}
