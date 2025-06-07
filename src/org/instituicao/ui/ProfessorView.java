package org.instituicao.ui;

import org.instituicao.controller.ProfessorController;
import org.instituicao.dto.FuncionarioDTO;

public class ProfessorView extends BaseView {
    private final ProfessorController professorController;

    public ProfessorView() {
        this.professorController = new ProfessorController();
    }

    /**
     * Tela de login do professor.
     */
    public void telaLogin() {
        exibirCabecalho("Realizar login");
        System.out.print("Email corporativo: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        FuncionarioDTO funcionario = professorController.login(email, senha);
        if (funcionario != null) {
            System.out.println("\nBem vindo(a) " + funcionario.getNome() + "!");

        } else {
            exibirErro("Credenciais inválidas.");
        }
    }
}
