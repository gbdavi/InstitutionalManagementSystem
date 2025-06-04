package org.instituicao.dto;

public class CursoDTO {
    private final int id;
    private final String nome;
    private final int idInstituicao;

    public CursoDTO(int id, String nome, int idInstituicao) {
        this.id = id;
        this.nome = nome;
        this.idInstituicao = idInstituicao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }
}
