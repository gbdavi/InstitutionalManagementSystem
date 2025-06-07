package org.instituicao.ui;

public class UI extends BaseView {

    private final AlunoView alunoView;
    private final ProfessorView professorView;
    private final AdminView adminView;

    public UI() {
        this.alunoView = new AlunoView();
        this.professorView = new ProfessorView();
        this.adminView = new AdminView();
    }

    /**
     * Loop base do menu principal.
     */
    public void run() {
        while (true) {
            exibirCabecalho("Selecionar ação");
            System.out.print(
                    "1 - Entrar como aluno"
                + "\n2 - Entrar como professor"
                + "\n3 - Entrar como administrador"
                + "\n4 - Encerrar aplicação"
                + "\n> "
            );
            switch (scanner.nextLine()) {
                case "1" -> {
                    alunoView.telaLogin();
                }
                case "2" -> {
                    professorView.telaLogin();
                }
                case "3" -> {
                    adminView.telaLogin();
                }
                case "4" -> {
                    return;
                }
                default -> exibirErro("Opção inválida.");
            }
        }
    }
}
