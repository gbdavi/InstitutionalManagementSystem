package org.instituicao.entity;

import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class DisciplinaEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(1);
    private final int id;
    private String nome;
    private String ementa;
    private final InstituicaoEntity instituicaoEntity;

    public DisciplinaEntity(String nome, String ementa, InstituicaoEntity instituicaoEntity) {
        id = idGenerator.gerarProximoId();
        this.nome = nome;
        this.ementa = ementa;
        this.instituicaoEntity = instituicaoEntity;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public InstituicaoEntity getInstituicaoEntity() {
        return instituicaoEntity;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
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
		DisciplinaEntity other = (DisciplinaEntity) obj;
		return Objects.equals(id, other.getId());
    }
}
