package org.instituicao.dto;

import org.instituicao.entity.CursoEntity;

public class CursoDTO {
    private final int id;
    private final String nome;
    private final int idInstituicao;

    public CursoDTO(int id, String nome, int idInstituicao) {
        this.id = id;
        this.nome = nome;
        this.idInstituicao = idInstituicao;
    }

    public CursoDTO(CursoEntity curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.idInstituicao = curso.getInstituicaoEntity().getId();
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

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
