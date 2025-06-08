package org.instituicao.ui;

import org.instituicao.controller.AlunoController;
import org.instituicao.controller.CursoController;
import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.CursoDTO;

import java.util.List;

public class CursoView extends BaseView {
    private final CursoController cursoController;
    private final AlunoController alunoController;

    public CursoView() {
        this.cursoController = new CursoController();
        this.alunoController = new AlunoController();
    }


    public void telaAdicionarAluno(int idInstituicao) {
        try {
            exibirCabecalho("Adicionar aluno ao curso");

            exibirInfo("Cursos dispon�veis:");
            List<CursoDTO> cursosDisponiveis = cursoController.getCursos(idInstituicao);
            cursosDisponiveis.forEach(System.out::println);

            int idCurso = Integer.parseInt(solicitarCampo("\nId curso"));
            if (cursosDisponiveis.stream().noneMatch(cursoDTO -> cursoDTO.getId() == idCurso)) {
                exibirErro("Curso inv�lido!");
                return;
            }

            exibirInfo("\nAlunos dispon�veis:");
            List<AlunoDTO> alunosDisponiveis = alunoController.getAlunos(idInstituicao);
            alunosDisponiveis.forEach(System.out::println);

            int matriculaAluno = Integer.parseInt(solicitarCampo("\nMatr�cula aluno"));
            if (alunosDisponiveis.stream().noneMatch(alunoDTO -> alunoDTO.getMatricula() == matriculaAluno)) {
                exibirErro("Matr�cula inv�lida!");
                return;
            }

            if (cursoController.adicionarAluno(idCurso, matriculaAluno)) {
                exibirInfo("Aluno adicionado com sucesso.");
            } else {
                exibirErro("Aluno n�o p�de ser adicionado.");
            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }
}
