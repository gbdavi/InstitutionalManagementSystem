package org.instituicao.dto;

import java.util.ArrayList;

public class RelatorioAlunoDTO {

    private final AlunoDTO aluno;
    private final ArrayList<RelatorioCursoDTO> relatorioCursos;

    public RelatorioAlunoDTO(AlunoDTO aluno, ArrayList<RelatorioCursoDTO> relatorioCursos) {
        this.aluno = aluno;
        this.relatorioCursos = relatorioCursos;
    }

    public AlunoDTO getAluno() {
        return aluno;
    }

    public ArrayList<RelatorioCursoDTO> getRelatorioCursos() {
        return relatorioCursos;
    }
}
