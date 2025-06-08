package org.instituicao.dto;

import org.instituicao.entity.AvaliacaoEntity;

public class AvaliacaoDTO {
    private final int id;
    private final String descricao;
    private final int idTurma;

    public AvaliacaoDTO(int id, String descricao, int idTurma) {
        this.id = id;
        this.descricao = descricao;
        this.idTurma = idTurma;
    }

    public AvaliacaoDTO(AvaliacaoEntity avaliacao) {
        this.id = avaliacao.getId();
        this.descricao = avaliacao.getDescricao();
        this.idTurma = avaliacao.getTurma().getId();
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdTurma() {
        return idTurma;
    }
}
