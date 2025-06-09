package org.instituicao.dto;

import org.instituicao.entity.AvaliacaoEntity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaliacaoDTO other = (AvaliacaoDTO) obj;
		return Objects.equals(id, other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
