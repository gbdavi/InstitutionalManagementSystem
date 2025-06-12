package org.instituicao.ui;

import org.instituicao.controller.AlunoController;
import org.instituicao.controller.InstituicaoController;
import org.instituicao.dto.*;
import org.instituicao.util.data.DataUtils;

import java.time.LocalDate;
import java.util.List;


public class AlunoView extends BaseView {
    private final AlunoController alunoController;
    private final InstituicaoController instituicaoController;
    private final AvaliacaoView avaliacaoView;

    public AlunoView() {
        this.alunoController = new AlunoController();
        this.instituicaoController = new InstituicaoController();
        this.avaliacaoView = new AvaliacaoView();
    }

    /**
     * Tela de login do aluno.
     */
    public void telaLogin() {
        exibirCabecalho("Realizar login");
        AlunoDTO aluno = alunoController.login(
                solicitarCampo("Email acad�mico"),
                solicitarCampo("Senha"));
        if (aluno != null) {
            System.out.println("\nBem vindo(a) " + aluno.getNome() + "!");
            menuAluno(aluno);
        } else {
            exibirErro("Credenciais inv�lidas.");
        }
    }

    /**
     * Menu principal do aluno.
     * @param alunoDTO aluno que executar� as a��es.
     */
    private void menuAluno(AlunoDTO alunoDTO) {
        while (true) {
            exibirCabecalho("Selecionar a��o - Aluno: " + alunoDTO.getMatricula());
            System.out.print(
                    "1 - Exibir relat�rio"
                + "\n2 - Entregar avalia��o"
                + "\n3 - Voltar"
                + "\n> "
            );
            switch (scanner.nextLine()) {
                case "1" -> {
                    RelatorioAlunoDTO relatorioAlunoDTO = alunoController.getRelatorio(alunoDTO.getMatricula());
                    exibirRelatorio(relatorioAlunoDTO);
                }
                case "2" -> {
                    avaliacaoView.telaEntregarAvaliacao(alunoDTO.getMatricula());
                }
                case "3" -> {
                    return;
                }
                default -> exibirErro("Op��o inv�lida.");
            }
        }
    }

    /**
     * Tela para cadastro de aluno no banco de dados.
     */
    public void telaCadastro() {
        try {
            exibirCabecalho("Cadastrar aluno");
            exibirInfo("\n", "Institui��es dispon�veis:");
            List<InstituicaoDTO> instituicoesDisponiveis = instituicaoController.getInstituicoes();
            instituicoesDisponiveis.forEach(System.out::println);

            int idInstituicao = Integer.parseInt(solicitarCampo("\nId institui��o"));
            if (instituicoesDisponiveis.stream().noneMatch(instituicaoDTO -> instituicaoDTO.getId() == idInstituicao)) {
                exibirErro("Institui��o inv�lida!");
                return;
            }

            AlunoCadastroDTO alunoCadastroDTO = new AlunoCadastroDTO(
                    solicitarCampo("Cpf"),
                    solicitarCampo("Nome"),
                    DataUtils.processarDataNascimento(solicitarCampo("Data Nascimento (dd/MM/yyyy)")),
                    solicitarCampo("Email"),
                    idInstituicao,
                    solicitarCampo("Senha")
            );

            exibirInfo("\n", "Revisar dados de cadastro.");
            System.out.println(alunoCadastroDTO);

            boolean entradaValidada = false;
            do {
                String dadosConferem = solicitarCampo("\nDados conferem (Sim/N�o)").toUpperCase();
                switch (dadosConferem) {
                    case "SIM" -> {
                        AlunoDTO alunoDTO = alunoController.cadastrar(alunoCadastroDTO);
                        if (alunoDTO != null) {
                            exibirInfo("\n", "Aluno criado com sucesso.");
                            System.out.println(alunoDTO);
                        } else {
                            exibirErro("\n", "Aluno n�o p�de ser criado.");
                        }
                        entradaValidada = true;
                    }
                    case "N�O" -> {
                        exibirInfo("Cadastro cancelado.");
                        entradaValidada = true;
                    }
                    default -> exibirErro("Valor inv�lido!");
                }
            } while (!entradaValidada);
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }

    /**
     * Tela para alterar dados de aluno no banco de dados.
     */
    public void telaAlterarDadosPessoais(AlunoDTO alunoDTO) {
        try {
            exibirCabecalho("Alterar dados pessoais do aluno");
            exibirInfo("\n", "Deixe vazio os valores que n�o deseja alterar.");
            String cpfInput = solicitarCampo("Cpf");
            String cpf = !cpfInput.isBlank() ? cpfInput : alunoDTO.getCpf();

            String nomeInput = solicitarCampo("Nome");
            String nome = !nomeInput.isBlank() ? nomeInput : alunoDTO.getNome();

            String dataNascimentoInput = solicitarCampo("Data Nascimento (dd/MM/yyyy)");
            LocalDate dataNascimento = !dataNascimentoInput.isBlank() ? DataUtils.processarDataNascimento(dataNascimentoInput) : alunoDTO.getDataNascimento();

            String emailInput = solicitarCampo("Email");
            String email = !emailInput.isBlank() ? emailInput : alunoDTO.getEmail();

            PessoaDTO dadosAlterados = new PessoaDTO(cpf, nome, dataNascimento, email);

            exibirInfo("\n", "Revisar dados.");
            System.out.println(dadosAlterados);

            boolean entradaValidada = false;
            do {
                String dadosConferem = solicitarCampo("\nDados conferem (Sim/N�o)").toUpperCase();
                switch (dadosConferem) {
                    case "SIM" -> {
                        if (alunoController.alterarDadosPessoais(alunoDTO.getMatricula(), dadosAlterados)) {
                            exibirInfo("Dados alterados com sucesso.");
                        } else {
                            exibirErro("Dados n�o puderam ser alterados.");
                        }
                        entradaValidada = true;
                    }
                    case "N�O" -> {
                        exibirInfo("Cadastro cancelado.");
                        entradaValidada = true;
                    }
                    default -> exibirErro("Valor inv�lido!");
                }
            } while (!entradaValidada);
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }

    /**
     * Exibir dados formatados do relat�rio do aluno.
     * @param relatorioAlunoDTO relat�rio do aluno.
     */
    public void exibirRelatorio(RelatorioAlunoDTO relatorioAlunoDTO) {
        System.out.println("Aluno: " + relatorioAlunoDTO.getMatricula() + " - " + relatorioAlunoDTO.getNome());
        System.out.println("* Cursos:");
        for (RelatorioCursoDTO relatorioCurso : relatorioAlunoDTO.getRelatorioCursos()) {
            System.out.println("\t- " + relatorioCurso.getCurso().getNome());
            System.out.println("\t\t* Disciplina" + " ".repeat(50) + " | M�dia | Situa��o");
            for (RelatorioDisciplinaDTO relatorioDisciplina : relatorioCurso.getRelatorioDisciplinas()) {
                if (relatorioDisciplina.getMedia() != null) {
                    System.out.printf("\t\t- %-60s | %5.2f | %s \n", relatorioDisciplina.getDisciplina().getNome(), relatorioDisciplina.getMedia(), relatorioDisciplina.getSituacao());
                } else {
                    System.out.printf("\t\t- %-60s |   -   | %s \n", relatorioDisciplina.getDisciplina().getNome(), relatorioDisciplina.getSituacao());
                }
            }
        }
    }

    /**
     * Solicita dados do aluno a partir da matr�cula.
     * @return retorna aluno se encontrado, caso contr�rio null.
     */
    public AlunoDTO solicitarAluno() {
        try {
            int matriculaAluno = Integer.parseInt(solicitarCampo("\nMatr�cula aluno"));
            AlunoDTO aluno = alunoController.getAluno(matriculaAluno);
            if (aluno == null) {
                exibirErro("Aluno n�o encontrado.");
                return null;
            }
            return aluno;
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
            return null;
        }
    }
}
