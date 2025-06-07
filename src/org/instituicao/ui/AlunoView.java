package org.instituicao.ui;

import org.instituicao.controller.AlunoController;
import org.instituicao.controller.InstituicaoController;
import org.instituicao.dto.AlunoCadastroDTO;
import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.InstituicaoDTO;
import org.instituicao.util.data.DataUtils;

import java.util.List;


public class AlunoView extends BaseView {
    private final AlunoController alunoController;
    private final InstituicaoController instituicaoController;

    public AlunoView() {
        this.alunoController = new AlunoController();
        this.instituicaoController = new InstituicaoController();
    }

    /**
     * Tela de login do aluno.
     */
    public void telaLogin() {
        exibirCabecalho("Realizar login");
        AlunoDTO aluno = alunoController.login(
                solicitarCampo("Email acadêmico"),
                solicitarCampo("Senha"));
        if (aluno != null) {
            System.out.println("\nBem vindo(a) " + aluno.getNome() + "!");
//            menuAluno(aluno);
        } else {
            exibirErro("Credenciais inválidas.");
        }
    }

    /**
     * Tela para cadastro de aluno na base de dados.
     */
    public void telaCadastro() {
        try {
            exibirCabecalho("Cadastrar aluno");

            exibirInfo("Instituições disponíveis:");
            List<InstituicaoDTO> instituicoesDisponiveis = instituicaoController.getInstituicoes();
            instituicoesDisponiveis.forEach(System.out::println);

            int idInstituicao = Integer.parseInt(solicitarCampo("\nId instituição"));
            if (instituicoesDisponiveis.stream().noneMatch(instituicaoDTO -> instituicaoDTO.getId() == idInstituicao)) {
                exibirErro("Instituição inválida!");
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

            while (true) {
                String dadosConferem = solicitarCampo("\nDados conferem (Sim/Não)").toUpperCase();
                switch (dadosConferem) {
                    case "SIM" -> {
                        AlunoDTO alunoDTO = alunoController.cadastrar(alunoCadastroDTO);
                        exibirInfo("\n", "Aluno criado com sucesso.");
                        System.out.println(alunoDTO);
                        return;
                    }
                    case "NÃO" -> {
                        exibirInfo("Cadastro cancelado.");
                        return;
                    }
                    default -> exibirErro("Valor inválido!");
                }

            }
        } catch (Exception e) {
            exibirErro("Valor inválido!");
        }
    }
}
