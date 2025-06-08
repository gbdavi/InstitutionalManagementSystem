package org.instituicao.dto;

import java.util.List;

public class RelatorioAlunoDTO {

    private final int matricula;
    private final String nome;
    private final List<RelatorioCursoDTO> relatorioCursos;

    public RelatorioAlunoDTO(int matricula, String nome,  List<RelatorioCursoDTO> relatorioCursos) {
        this.nome = nome;
        this.matricula = matricula;
        this.relatorioCursos = relatorioCursos;
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public List<RelatorioCursoDTO> getRelatorioCursos() {
        return relatorioCursos;
    }
}
