package org.instituicao.ui;

import org.instituicao.controller.InstituicaoController;
import org.instituicao.controller.ProfessorController;
import org.instituicao.controller.TurmaController;
import org.instituicao.dto.FuncionarioCadastroDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.dto.InstituicaoDTO;
import org.instituicao.dto.TurmaDTO;
import org.instituicao.util.data.DataUtils;

import java.util.List;

public class ProfessorView extends BaseView {
    private final ProfessorController professorController;
    private final InstituicaoController instituicaoController;
    private final AvaliacaoView avaliacaoView;

    public ProfessorView() {
        this.professorController = new ProfessorController();
        this.instituicaoController = new InstituicaoController();
        this.avaliacaoView = new AvaliacaoView();
    }

    /**
     * Tela de login do professor.
     */
    public void telaLogin() {
        exibirCabecalho("Realizar login");
        FuncionarioDTO professor = professorController.login(
                solicitarCampo("Email corporativo"),
                solicitarCampo("Senha"));
        if (professor != null) {
            System.out.println("\nBem vindo(a) " + professor.getNome() + "!");
            menuProfessor(professor);
        } else {
            exibirErro("Credenciais inv�lidas.");
        }
    }

    /**
     * Menu principal do professor.
     * @param professorDTO professor que executar� as a��es.
     */
    private void menuProfessor(FuncionarioDTO professorDTO) {
        while (true) {
            exibirCabecalho("Selecionar a��o - Professor: " + professorDTO.getMatricula());
            System.out.print(
                    "1 - Avaliar entregas"
                + "\n2 - Cadastrar avalia��o"
                + "\n3 - Voltar"
                + "\n> "
            );
            switch (scanner.nextLine()) {
                case "1" -> {
                    avaliacaoView.telaAvaliarEntrega(professorDTO.getMatricula());
                }
                case "2" -> {
                    avaliacaoView.telaCadastrarAvaliacao(professorDTO.getMatricula());
                }
                case "3" -> {
                    return;
                }
                default -> exibirErro("Op��o inv�lida.");
            }
        }
    }

    /**
     * Tela para cadastro de professor no banco de dados.
     */
    public void telaCadastro() {
        try {
            exibirCabecalho("Cadastrar professor");
            exibirInfo("\n", "Institui��es dispon�veis:");
            List<InstituicaoDTO> instituicoesDisponiveis = instituicaoController.getInstituicoes();
            instituicoesDisponiveis.forEach(System.out::println);

            int idInstituicao = Integer.parseInt(solicitarCampo("\nId institui��o"));
            if (instituicoesDisponiveis.stream().noneMatch(instituicaoDTO -> instituicaoDTO.getId() == idInstituicao)) {
                exibirErro("Institui��o inv�lida!");
                return;
            }

            FuncionarioCadastroDTO professorCadastroDTO = new FuncionarioCadastroDTO(
                    solicitarCampo("Cpf"),
                    solicitarCampo("Nome"),
                    DataUtils.processarDataNascimento(solicitarCampo("Data Nascimento (dd/MM/yyyy)")),
                    solicitarCampo("Email"),
                    idInstituicao,
                    solicitarCampo("Senha")
            );

            exibirInfo("\n", "Revisar dados de cadastro.");
            System.out.println(professorCadastroDTO);

            boolean entradaValida = false;
            do {
                String dadosConferem = solicitarCampo("\nDados conferem (Sim/N�o)").toUpperCase();
                switch (dadosConferem) {
                    case "SIM" -> {
                        FuncionarioDTO professorDTO = professorController.cadastrar(professorCadastroDTO);
                        exibirInfo("\n", "Professor criado com sucesso.");
                        System.out.println(professorDTO);
                        entradaValida = true;
                    }
                    case "N�O" -> {
                        exibirInfo("Cadastro cancelado.");
                        entradaValida = true;
                    }
                    default -> exibirErro("Valor inv�lido!");
                }
            } while (!entradaValida);
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }
}
