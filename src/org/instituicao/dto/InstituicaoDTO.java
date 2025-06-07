package org.instituicao.dto;

import org.instituicao.entity.InstituicaoEntity;

public class InstituicaoDTO {
    private final int id;
    private final String nome;
    private final String aliasCorporativo;
    private final String aliasAcademico;

    public InstituicaoDTO(int id, String nome, String aliasCorporativo, String aliasAcademico) {
        this.id = id;
        this.nome = nome;
        this.aliasCorporativo = aliasCorporativo;
        this.aliasAcademico = aliasAcademico;
    }

    public InstituicaoDTO(InstituicaoEntity instituicao) {
        this.id = instituicao.getId();
        this.nome = instituicao.getNome();
        this.aliasCorporativo = instituicao.getAliasCorporativo();
        this.aliasAcademico = instituicao.getAliasAcademico();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAliasCorporativo() {
        return aliasCorporativo;
    }

    public String getAliasAcademico() {
        return aliasAcademico;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
