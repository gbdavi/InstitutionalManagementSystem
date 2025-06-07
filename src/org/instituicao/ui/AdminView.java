package org.instituicao.ui;

import org.instituicao.controller.AdminController;
import org.instituicao.dto.FuncionarioDTO;

public class AdminView extends BaseView {

    private final AdminController adminController;

    public AdminView() {
        this.adminController = new AdminController();
    }

    /**
     * Tela de login do admin.
     */
    public void telaLogin() {
        exibirCabecalho("Realizar login");
        System.out.print("Email corporativo: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        FuncionarioDTO funcionario = adminController.login(email, senha);
        if (funcionario != null) {
            System.out.println("\nBem vindo(a) " + funcionario.getNome() + "!");

        } else {
            exibirErro("Credenciais inválidas.");
        }
    }

}
