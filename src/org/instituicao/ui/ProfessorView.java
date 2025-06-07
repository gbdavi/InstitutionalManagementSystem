package org.instituicao.ui;

import org.instituicao.controller.InstituicaoController;
import org.instituicao.controller.ProfessorController;
import org.instituicao.dto.FuncionarioCadastroDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.dto.InstituicaoDTO;
import org.instituicao.util.data.DataUtils;

import java.util.List;

public class ProfessorView extends BaseView {
    private final ProfessorController professorController;
    private final InstituicaoController instituicaoController;

    public ProfessorView() {
        this.professorController = new ProfessorController();
        this.instituicaoController = new InstituicaoController();
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

        } else {
            exibirErro("Credenciais inv�lidas.");
        }
    }

    /**
     * Tela para cadastro de professor na base de dados.
     */
    public void telaCadastro() {
        try {
            exibirCabecalho("Cadastrar professor");

            exibirInfo("Institui��es dispon�veis:");
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

            while (true) {
                String dadosConferem = solicitarCampo("\nDados conferem (Sim/N�o)").toUpperCase();
                switch (dadosConferem) {
                    case "SIM" -> {
                        FuncionarioDTO professorDTO = professorController.cadastrar(professorCadastroDTO);
                        exibirInfo("\n", "Professor criado com sucesso.");
                        System.out.println(professorDTO);
                        return;
                    }
                    case "N�O" -> {
                        exibirInfo("Cadastro cancelado.");
                        return;
                    }
                    default -> exibirErro("Valor inv�lido!");
                }

            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }
}
