package org.instituicao.entity;

import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class DisciplinaEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(0);
    private final int id;
    private String nome;
    private String ementa;
    private HashSet<TurmaEntity> turmaEntities = new HashSet<>();

    public DisciplinaEntity(String nome, String ementa) {
        id = idGenerator.gerarProximoId();
        this.nome = nome;
        this.ementa = ementa;
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

    public Set<TurmaEntity> getTurmas() {
        return Collections.unmodifiableSet(turmaEntities);
    }

    public boolean adicionarTurma(TurmaEntity turmaEntity) {
        return turmaEntities.add(turmaEntity);
    }

    public boolean removerTurma(TurmaEntity turmaEntity) {
        return turmaEntities.remove(turmaEntity);
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
