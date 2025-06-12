package org.instituicao.entity;

import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class CursoEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(1);
    private final int id;
    private String nome;
    private HashSet<DisciplinaEntity> disciplinaEntities = new HashSet<>();
    private final InstituicaoEntity instituicaoEntity;

    public CursoEntity(String nome, InstituicaoEntity instituicaoEntity) {
        id = idGenerator.gerarProximoId();
        this.nome = nome;
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

    /**
     * Disciplinas adicionadas ao curso.
     * @return set não modificável de disciplinas.
     */
    public Set<DisciplinaEntity> getDisciplinas() {
        return Collections.unmodifiableSet(disciplinaEntities);
    }

    /**
     * Adiciona a disciplina no set de disciplinas.
     */
    public boolean adicionarDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntities.add(disciplinaEntity);
    }

    /**
     * Remove a disciplina do set de disciplinas.
     */
    public boolean removerDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntities.remove(disciplinaEntity);
    }

    public InstituicaoEntity getInstituicaoEntity() {
        return instituicaoEntity;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

    /**
     * Gera o hashcode da instância.
     * Obs.: necessário para utilização do HashSet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara a instância atual com outra fornecida.
     * Obs.: necessário para utilização do HashSet.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CursoEntity other = (CursoEntity) obj;
		return Objects.equals(id, other.getId());
    }
}
