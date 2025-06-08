package org.instituicao.dto;

import org.instituicao.entity.DisciplinaEntity;

public class DisciplinaDTO {
    private final int id;
    private final String nome;
    private final String ementa;
    private final int idInstituicao;

    public DisciplinaDTO(int id, String nome, String ementa, int idInstituicao) {
        this.id = id;
        this.nome = nome;
        this.ementa = ementa;
        this.idInstituicao = idInstituicao;
    }

    public DisciplinaDTO(DisciplinaEntity disciplinaEntity) {
        this.id = disciplinaEntity.getId();
        this.nome = disciplinaEntity.getNome();
        this.ementa = disciplinaEntity.getEmenta();
        this.idInstituicao = getIdInstituicao();
    }

    public int getId() {
        return id;
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
