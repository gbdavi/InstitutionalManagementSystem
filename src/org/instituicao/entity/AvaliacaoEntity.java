package org.instituicao.entity;

import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Objects;


public class AvaliacaoEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(1);

    private int id;
    private String descricao;
    private TurmaEntity turmaEntity;

    public AvaliacaoEntity(String descricao, TurmaEntity turmaEntity) {
        this.id = idGenerator.gerarProximoId();
        this.descricao = descricao;
        this.turmaEntity = turmaEntity;

        turmaEntity.adicionarAvaliacao(this);
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TurmaEntity getTurma() {
        return turmaEntity;
    }

    @Override
    public String toString() {
        return id + " - " + descricao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaliacaoEntity other = (AvaliacaoEntity) obj;
		return Objects.equals(id, other.getId());
    }
}
