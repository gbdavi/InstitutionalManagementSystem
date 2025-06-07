package org.instituicao.entity;

import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class AvaliacaoEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(1);

    private int id;
    private String descricao;
    private TurmaEntity turmaEntity;
    private HashSet<EntregaEntity> entregaEntities = new HashSet<>();

    public AvaliacaoEntity(String descricao, TurmaEntity turmaEntity) {
        this.id = idGenerator.gerarProximoId();
        this.descricao = descricao;
        this.turmaEntity = turmaEntity;

        turmaEntity.adicionarAvaliacao(this);
        for (AlunoEntity alunoEntity : turmaEntity.getAlunos()) {
            adicionarEntrega(alunoEntity);
        }
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

    public Set<EntregaEntity> getEntregas() {
        return Collections.unmodifiableSet(entregaEntities);
    }

    public boolean adicionarEntrega(AlunoEntity alunoEntity) {
        return entregaEntities.add(new EntregaEntity(alunoEntity, this));
    }

    @Override
    public String toString() {
        return id + " - " + descricao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
