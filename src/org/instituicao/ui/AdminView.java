package org.instituicao.ui;

import org.instituicao.controller.AdminController;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.entity.AdminEntity;

public class AdminView extends BaseView {

    private final AdminController adminController;
    private final AlunoView alunoView;
    private final ProfessorView professorView;

    public AdminView() {
        this.adminController = new AdminController();
        this.alunoView = new AlunoView();
        this.professorView = new ProfessorView();
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

    public void telaAcoes(FuncionarioDTO admin) {
        while (true) {
            exibirCabecalho("Selecionar ação");
            System.out.print(
                    "1 - Cadastrar aluno"
                + "\n2 - Cadastrar professor"
                + "\n3 - Voltar"
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
                    return;
                }
                default -> exibirErro("Opção inválida.");
            }
        }
    }
}
