package org.instituicao.dto;

public class InstituicaoCadastroDTO {
    private final String nome;
    private final String aliasCorporativo;
    private final String aliasAcademico;

    public InstituicaoCadastroDTO(String nome, String aliasCorporativo, String aliasAcademico) {
        this.nome = nome;
        this.aliasCorporativo = aliasCorporativo;
        this.aliasAcademico = aliasAcademico;
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
}
