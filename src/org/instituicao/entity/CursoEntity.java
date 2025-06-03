package org.instituicao.entity;

import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class CursoEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(0);
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

    public Set<DisciplinaEntity> getDisciplinas() {
        return Collections.unmodifiableSet(disciplinaEntities);
    }

    public boolean adicionarDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntities.add(disciplinaEntity);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
