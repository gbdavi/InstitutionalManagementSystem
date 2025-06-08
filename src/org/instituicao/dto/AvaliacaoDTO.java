package org.instituicao.dto;

import org.instituicao.entity.AvaliacaoEntity;

public class AvaliacaoDTO {
    private final int id;
    private final String descricao;
    private final TurmaDTO turma;

    public AvaliacaoDTO(int id, String descricao, TurmaDTO turma) {
        this.id = id;
        this.descricao = descricao;
        this.turma = turma;
    }

    public AvaliacaoDTO(AvaliacaoEntity avaliacao) {
        this.id = avaliacao.getId();
        this.descricao = avaliacao.getDescricao();
        this.turma = new TurmaDTO(avaliacao.getTurma());
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public TurmaDTO getTurma() {
        return turma;
    }

    @Override
    public String toString() {
        return id + " - " + descricao + " | " + turma.getDisciplina().getNome();
    }
}
