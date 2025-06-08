package org.instituicao.dto;

public class CursoCadastroDTO {
    private final String nome;
    private final int idInstituicao;

    public CursoCadastroDTO(String nome, int idInstituicao) {
        this.nome = nome;
        this.idInstituicao = idInstituicao;
    }

    public String getNome() {
        return nome;
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }
}
