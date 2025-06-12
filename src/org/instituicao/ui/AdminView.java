package org.instituicao.ui;

import org.instituicao.controller.AdminController;
import org.instituicao.controller.AlunoController;
import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.dto.RelatorioAlunoDTO;

import java.util.List;

public class AdminView extends BaseView {

    private final AdminController adminController;
    private final AlunoController alunoController;
    private final AlunoView alunoView;
    private final ProfessorView professorView;
    private final CursoView cursoView;
    private final TurmaView turmaView;

    public AdminView() {
        this.adminController = new AdminController();
        this.alunoController = new AlunoController();
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
            menuAdmin(admin);

        } else {
            exibirErro("Credenciais inválidas.");
        }
    }

    /**
     * Menu principal do admin.
     * @param adminDTO admin que executará as ações.
     */
    public void menuAdmin(FuncionarioDTO adminDTO) {
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
                + "\n8 - Alterar dados pessoais do aluno"
                + "\n9 - Voltar"
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
                case "7" -> {
                    List<AlunoDTO> alunos = alunoController.getAlunos(adminDTO.getIdInstituicao());
                    alunos.forEach(aluno -> {
                        alunoView.exibirRelatorio(
                            alunoController.getRelatorio(aluno.getMatricula())
                        );
                    });
                }
                case "8" -> {
                    exibirInfo("\n", "Alunos disponíveis");
                    List<AlunoDTO> alunos = alunoController.getAlunos(adminDTO.getIdInstituicao());
                    alunos.forEach(System.out::println);
                    AlunoDTO aluno = alunoView.solicitarAluno();
                    if (aluno != null) {
                        alunoView.telaAlterarDadosPessoais(aluno);
                    }
                }
                case "9" -> {
                    return;
                }
                default -> exibirErro("Opção inválida.");
            }
        }
    }
}
