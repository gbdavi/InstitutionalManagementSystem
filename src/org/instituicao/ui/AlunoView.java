package org.instituicao.ui;

import org.instituicao.controller.AlunoController;
import org.instituicao.dto.AlunoDTO;


public class AlunoView extends BaseView {
    private final AlunoController alunoController;

    public AlunoView() {
        this.alunoController = new AlunoController();
    }

    /**
     * Tela de login do aluno.
     */
    public void telaLogin() {
        exibirCabecalho("Realizar login");
        System.out.print("Email acadêmico: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        AlunoDTO aluno = alunoController.login(email, senha);
        if (aluno != null) {
            System.out.println("\nBem vindo(a) " + aluno.getNome() + "!");
//            menuAluno(aluno);
        } else {
            exibirErro("Credenciais inválidas.");
        }
    }
}
