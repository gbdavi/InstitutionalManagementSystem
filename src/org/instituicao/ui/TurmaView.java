package org.instituicao.ui;

import org.instituicao.controller.AlunoController;
import org.instituicao.controller.ProfessorController;
import org.instituicao.controller.TurmaController;
import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.dto.TurmaDTO;

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

    public void telaAdicionarAluno(int idInstituicao) {
        try {
            exibirCabecalho("Adicionar aluno na turma");

            exibirInfo("Alunos disponíveis:");
            List<AlunoDTO> alunosDisponiveis = alunoController.getAlunos(idInstituicao);
            alunosDisponiveis.forEach(System.out::println);

            int matriculaAluno = Integer.parseInt(solicitarCampo("\nMatrícula aluno"));
            if (alunosDisponiveis.stream().noneMatch(alunoDTO -> alunoDTO.getMatricula() == matriculaAluno)) {
                exibirErro("Matrícula inválida!");
                return;
            }

            exibirInfo("\n", "Turmas disponíveis:");
            List<TurmaDTO> turmasDisponiveis = turmaController.getTurmasDisponiveisByAluno(matriculaAluno);
            turmasDisponiveis.forEach(System.out::println);

            int idTurma = Integer.parseInt(solicitarCampo("\nId turma"));
            if (turmasDisponiveis.stream().noneMatch(cursoDTO -> cursoDTO.getId() == idTurma)) {
                exibirErro("Turma inválida!");
                return;
            }

            if (turmaController.adicionarAluno(idTurma, matriculaAluno)) {
                exibirInfo("Aluno adicionado com sucesso.");
            } else {
                exibirErro("Aluno não pôde ser adicionado.");
            }
        } catch (Exception e) {
            exibirErro("Valor inválido!");
        }
    }

    public void telaAdicionarProfessor(int idInstituicao) {
        try {
            exibirCabecalho("Adicionar aluno na turma");

            exibirInfo("Professores disponíveis:");
            List<FuncionarioDTO> professoresDisponiveis = professorController.getProfessores(idInstituicao);
            professoresDisponiveis.forEach(System.out::println);

            int matriculaProfessor = Integer.parseInt(solicitarCampo("\nMatrícula professor"));
            if (professoresDisponiveis.stream().noneMatch(funcionarioDTO -> funcionarioDTO.getMatricula() == matriculaProfessor)) {
                exibirErro("Matrícula inválida!");
                return;
            }

            exibirInfo("\n", "Turmas disponíveis:");
            List<TurmaDTO> turmasDisponiveis = turmaController.getTurmasByInstituicao(idInstituicao);
            turmasDisponiveis.forEach(System.out::println);

            int idTurma = Integer.parseInt(solicitarCampo("\nId turma"));
            if (turmasDisponiveis.stream().noneMatch(cursoDTO -> cursoDTO.getId() == idTurma)) {
                exibirErro("Turma inválida!");
                return;
            }

            if (turmaController.adicionarProfessor(idTurma, matriculaProfessor)) {
                exibirInfo("Professor adicionado com sucesso.");
            } else {
                exibirErro("Professor não pôde ser adicionado.");
            }
        } catch (Exception e) {
            exibirErro("Valor inválido!");
        }
    }
}
