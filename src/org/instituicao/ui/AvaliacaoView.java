package org.instituicao.ui;

import org.instituicao.controller.AvaliacaoController;
import org.instituicao.dto.AvaliacaoDTO;

import java.util.List;

public class AvaliacaoView extends BaseView {
    private final AvaliacaoController avaliacaoController;

    public AvaliacaoView() {
        this.avaliacaoController = new AvaliacaoController();
    }

    public void telaEntregarAvaliacao(int matriculaAluno) {
        try {
            exibirCabecalho("Entregar avalia��o");
            exibirInfo("\n", "Avalia��es dispon�veis:");
            List<AvaliacaoDTO> avaliacoesDisponiveis = avaliacaoController.getAvaliacoesPendentesByAluno(matriculaAluno);
            avaliacoesDisponiveis.forEach(System.out::println);

            int idAvaliacao = Integer.parseInt(solicitarCampo("\nId avalia��o"));
            if (avaliacoesDisponiveis.stream().noneMatch(avaliacaoDTO -> avaliacaoDTO.getId() == idAvaliacao)) {
                exibirErro("Avalia��o inv�lida!");
                return;
            }

            String resposta = solicitarCampo("Resposta");
            if (avaliacaoController.entregarAvaliacao(idAvaliacao, matriculaAluno, resposta)) {
                exibirInfo("Avalia��o entregue com sucesso.");
            } else {
                exibirErro("Avalia��o n�o p�de ser entregue.");
            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }


    }
}
