package org.instituicao.dto;

import java.util.List;

public class RelatorioCursoDTO {

    private final CursoDTO curso;
    private final List<RelatorioDisciplinaDTO> relatorioDisciplinas;

    public RelatorioCursoDTO(CursoDTO curso, List<RelatorioDisciplinaDTO> relatorioDisciplinas) {
        this.curso = curso;
        this.relatorioDisciplinas = relatorioDisciplinas;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public List<RelatorioDisciplinaDTO> getRelatorioDisciplinas() {
        return relatorioDisciplinas;
    }
}
