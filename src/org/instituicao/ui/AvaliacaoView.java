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
            exibirCabecalho("Entregar avaliação");
            exibirInfo("\n", "Avaliações disponíveis:");
            List<AvaliacaoDTO> avaliacoesDisponiveis = avaliacaoController.getAvaliacoesPendentesByAluno(matriculaAluno);
            avaliacoesDisponiveis.forEach(System.out::println);

            int idAvaliacao = Integer.parseInt(solicitarCampo("\nId avaliação"));
            if (avaliacoesDisponiveis.stream().noneMatch(avaliacaoDTO -> avaliacaoDTO.getId() == idAvaliacao)) {
                exibirErro("Avaliação inválida!");
                return;
            }

            String resposta = solicitarCampo("Resposta");
            if (avaliacaoController.entregarAvaliacao(idAvaliacao, matriculaAluno, resposta)) {
                exibirInfo("Avaliação entregue com sucesso.");
            } else {
                exibirErro("Avaliação não pôde ser entregue.");
            }
        } catch (Exception e) {
            exibirErro("Valor inválido!");
        }


    }
}
