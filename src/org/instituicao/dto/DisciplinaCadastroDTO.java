package org.instituicao.dto;

public class DisciplinaCadastroDTO {
    private final String nome;
    private final String ementa;
    private final int idInstituicao;

    public DisciplinaCadastroDTO(String nome, String ementa, int idInstituicao) {
        this.nome = nome;
        this.ementa = ementa;
        this.idInstituicao = idInstituicao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmenta() {
        return ementa;
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }
}
