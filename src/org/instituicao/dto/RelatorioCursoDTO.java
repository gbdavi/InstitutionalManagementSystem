package org.instituicao.dto;

import java.util.ArrayList;

public class RelatorioCursoDTO {

    private final CursoDTO curso;
    private final ArrayList<RelatorioDisciplinaDTO> relatorioDisciplinas;

    public RelatorioCursoDTO(CursoDTO curso, ArrayList<RelatorioDisciplinaDTO> relatorioDisciplinas) {
        this.curso = curso;
        this.relatorioDisciplinas = relatorioDisciplinas;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public ArrayList<RelatorioDisciplinaDTO> getRelatorioDisciplinas() {
        return relatorioDisciplinas;
    }
}
