package org.instituicao.entity;

import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Objects;

public class InstituicaoEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(0);

    private int id;
    private String nome;
    private String aliasCorporativo;
    private String aliasAcademico;

    public InstituicaoEntity(String nome, String aliasCorporativo, String aliasAcademico) {
        this.id = idGenerator.gerarProximoId();
        this.nome = nome;
        this.aliasCorporativo = aliasCorporativo;
        this.aliasAcademico = aliasAcademico;
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

    public String getAliasCorporativo() {
        return aliasCorporativo;
    }

    public void setAliasCorporativo(String aliasCorporativo) {
        this.aliasCorporativo = aliasCorporativo;
    }

    public String getAliasAcademico() {
        return aliasAcademico;
    }

    public void setAliasAcademico(String aliasAcademico) {
        this.aliasAcademico = aliasAcademico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
