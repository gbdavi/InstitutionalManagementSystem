package org.instituicao.dto;

public class AvaliacaoCadastroDTO {
    private final String descricao;
    private final int idTurma;

    public AvaliacaoCadastroDTO(String descricao, int idTurma) {
        this.descricao = descricao;
        this.idTurma = idTurma;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdTurma() {
        return idTurma;
    }
}
