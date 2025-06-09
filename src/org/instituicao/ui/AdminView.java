package org.instituicao.ui;

import org.instituicao.controller.AdminController;
import org.instituicao.dto.FuncionarioDTO;

public class AdminView extends BaseView {

    private final AdminController adminController;
    private final AlunoView alunoView;
    private final ProfessorView professorView;
    private final CursoView cursoView;
    private final TurmaView turmaView;

    public AdminView() {
        this.adminController = new AdminController();
        this.alunoView = new AlunoView();
        this.professorView = new ProfessorView();
        this.cursoView = new CursoView();
        this.turmaView = new TurmaView();
    }

    /**
     * Tela de login do admin.
     */
    public void telaLogin() {
        exibirCabecalho("Realizar login");
        FuncionarioDTO admin = adminController.login(
                solicitarCampo("Email corporativo"),
                solicitarCampo("Senha"));
        if (admin != null) {
            System.out.println("\nBem vindo(a) " + admin.getNome() + "!");
            telaAcoes(admin);

        } else {
            exibirErro("Credenciais inválidas.");
        }
    }

    public void telaAcoes(FuncionarioDTO adminDTO) {
        while (true) {
            exibirCabecalho("Selecionar ação - Admin: " + adminDTO.getMatricula());
            System.out.print(
                    "1 - Cadastrar aluno"
                + "\n2 - Cadastrar professor"
                + "\n3 - Adicionar aluno ao curso"
                + "\n4 - Adicionar aluno a turma"
                + "\n5 - Adicionar professor a turma"
                + "\n6 - Alterar status da turma"
                + "\n7 - Listar relatório de todos os alunos da instituição"
                + "\n8 - Voltar"
                + "\n> "
            );
            switch (scanner.nextLine()) {
                case "1" -> {
                    alunoView.telaCadastro();
                }
                case "2" -> {
                    professorView.telaCadastro();
                }
                case "3" -> {
                    cursoView.telaAdicionarAluno(adminDTO.getIdInstituicao());
                }
                case "4" -> {
                    turmaView.telaAdicionarAluno(adminDTO.getIdInstituicao());
                }
                case "5" -> {
                    turmaView.telaAdicionarProfessor(adminDTO.getIdInstituicao());
                }
                case "6" -> {
                    turmaView.telaAlterarStatus(adminDTO.getIdInstituicao());
                }
                case "8" -> {
                    return;
                }
                default -> exibirErro("Opção inválida.");
            }
        }
    }
}
