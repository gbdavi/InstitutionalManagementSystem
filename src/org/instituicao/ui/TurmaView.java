package org.instituicao.ui;

import org.instituicao.controller.AlunoController;
import org.instituicao.controller.ProfessorController;
import org.instituicao.controller.TurmaController;
import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.dto.TurmaDTO;
import org.instituicao.type.StatusTurma;

import java.util.List;

public class TurmaView extends BaseView {
    private final TurmaController turmaController;
    private final AlunoController alunoController;
    private final ProfessorController professorController;

    public TurmaView() {
        this.turmaController = new TurmaController();
        this.alunoController = new AlunoController();
        this.professorController = new ProfessorController();
    }

    /**
     * Tela para adicionar aluno na turma no banco de dados.
     */
    public void telaAdicionarAluno(int idInstituicao) {
        try {
            exibirCabecalho("Adicionar aluno na turma");
            exibirInfo("\n", "Alunos dispon�veis:");
            List<AlunoDTO> alunosDisponiveis = alunoController.getAlunos(idInstituicao);
            alunosDisponiveis.forEach(System.out::println);

            int matriculaAluno = Integer.parseInt(solicitarCampo("\nMatr�cula aluno"));
            if (alunosDisponiveis.stream().noneMatch(alunoDTO -> alunoDTO.getMatricula() == matriculaAluno)) {
                exibirErro("Matr�cula inv�lida!");
                return;
            }

            exibirInfo("\n", "Turmas dispon�veis:");
            List<TurmaDTO> turmasDisponiveis = turmaController.getTurmasDisponiveisByAluno(matriculaAluno);
            turmasDisponiveis.forEach(System.out::println);

            int idTurma = Integer.parseInt(solicitarCampo("\nId turma"));
            if (turmasDisponiveis.stream().noneMatch(cursoDTO -> cursoDTO.getId() == idTurma)) {
                exibirErro("Turma inv�lida!");
                return;
            }

            if (turmaController.adicionarAluno(idTurma, matriculaAluno)) {
                exibirInfo("Aluno adicionado com sucesso.");
            } else {
                exibirErro("Aluno n�o p�de ser adicionado.");
            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }


    /**
     * Tela para adicionar professor para ministrar turma no banco de dados.
     */
    public void telaAdicionarProfessor(int idInstituicao) {
        try {
            exibirCabecalho("Adicionar professor na turma");
            exibirInfo("\n", "Professores dispon�veis:");
            List<FuncionarioDTO> professoresDisponiveis = professorController.getProfessores(idInstituicao);
            professoresDisponiveis.forEach(System.out::println);

            int matriculaProfessor = Integer.parseInt(solicitarCampo("\nMatr�cula professor"));
            if (professoresDisponiveis.stream().noneMatch(funcionarioDTO -> funcionarioDTO.getMatricula() == matriculaProfessor)) {
                exibirErro("Matr�cula inv�lida!");
                return;
            }

            exibirInfo("\n", "Turmas dispon�veis:");
            List<TurmaDTO> turmasDisponiveis = turmaController.getTurmasByInstituicao(idInstituicao);
            turmasDisponiveis.forEach(System.out::println);

            int idTurma = Integer.parseInt(solicitarCampo("\nId turma"));
            if (turmasDisponiveis.stream().noneMatch(cursoDTO -> cursoDTO.getId() == idTurma)) {
                exibirErro("Turma inv�lida!");
                return;
            }

            if (turmaController.adicionarProfessor(idTurma, matriculaProfessor)) {
                exibirInfo("Professor adicionado com sucesso.");
            } else {
                exibirErro("Professor n�o p�de ser adicionado.");
            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }

    /**
     * Tela para alterar status da turma no banco de dados.
     */
    public void telaAlterarStatus(int idInstituicao) {
        try {
            exibirCabecalho("Alterar status da turma");
            exibirInfo("\n", "Turmas dispon�veis:");
            List<TurmaDTO> turmasDisponiveis = turmaController.getTurmasByInstituicao(idInstituicao);
            turmasDisponiveis.forEach(System.out::println);

            int idTurma = Integer.parseInt(solicitarCampo("\nId turma"));
            if (turmasDisponiveis.stream().noneMatch(cursoDTO -> cursoDTO.getId() == idTurma)) {
                exibirErro("Turma inv�lida!");
                return;
            }

            exibirInfo("\n", "Status de turma dispon�veis");
            System.out.println("1 - N�o iniciada" +
                    "\n2 - Em andamento" +
                    "\n3 - Conclu�da");

            String nStatusSelecionado = solicitarCampo("\nN�mero status");
            StatusTurma statusSelecionado;
            switch (nStatusSelecionado) {
                case "1" -> {
                    statusSelecionado = StatusTurma.NAO_INICIADA;
                }
                case "2" -> {
                    statusSelecionado = StatusTurma.EM_ANDAMENTO;
                }
                case "3" -> {
                    statusSelecionado = StatusTurma.CONCLUIDA;
                }
                default -> {
                    exibirErro("Status inv�lido!");
                    return;
                }
            }

            if (turmaController.alterarStatus(idTurma, statusSelecionado)) {
                exibirInfo("Status alterado com sucesso.");
            } else {
                exibirErro("Status n�o p�de ser alterado.");
            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }
}
