package org.instituicao.ui;

import org.instituicao.controller.AvaliacaoController;
import org.instituicao.controller.EntregaController;
import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.AvaliacaoDTO;
import org.instituicao.dto.EntregaDTO;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AvaliacaoView extends BaseView {
    private final AvaliacaoController avaliacaoController;
    private final EntregaController entregaController;

    public AvaliacaoView() {
        this.avaliacaoController = new AvaliacaoController();
        this.entregaController =new EntregaController();
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

    public void telaAvaliarEntrega(int matriculaProfessor) {
        try {
            exibirCabecalho("Avaliar entregas");

            exibirInfo("\n", "Avaliações disponíveis:");
            List<EntregaDTO> entregasPendentes = entregaController.getEntregasPendentesByProfessor(matriculaProfessor);
            List<AvaliacaoDTO> avaliacoesDisponiveis = entregasPendentes.stream()
                .map(EntregaDTO::getAvaliacao)
                .collect(Collectors.toCollection(HashSet::new)).stream()
                .sorted(Comparator.comparing(AvaliacaoDTO::getDescricao))
                .toList();
            avaliacoesDisponiveis.forEach(System.out::println);

            int idAvaliacao = Integer.parseInt(solicitarCampo("\nId avaliação"));
            if (!avaliacoesDisponiveis.stream().anyMatch(avaliacaoDTO -> avaliacaoDTO.getId() == idAvaliacao)) {
                exibirErro("Avaliação inválida!");
                return;
            }

            List<AlunoDTO> alunosDisponiveis = entregasPendentes.stream()
                .filter(entregaDTO -> entregaDTO.getAvaliacao().getId() == idAvaliacao)
                .map(EntregaDTO::getAluno)
                .toList();
            alunosDisponiveis.forEach(System.out::println);

            int matriculaAluno = Integer.parseInt(solicitarCampo("\nMatrícula aluno"));
            Optional<EntregaDTO> entregaAluno = entregasPendentes.stream()
                .filter(entregaDTO -> entregaDTO.getAluno().getMatricula() == matriculaAluno && entregaDTO.getAvaliacao().getId() == idAvaliacao)
                .findFirst();
            if (!entregaAluno.isPresent()) {
                exibirErro("Aluno inválido!");
                return;
            }

            System.out.println("\nResposta do aluno: " + entregaAluno.get().getResposta());

            float nota = Float.parseFloat(solicitarCampo("\nNota").replace(",", "."));
            if (nota < 0 || nota > 10) {
                exibirErro("A nota deve ser de 0 a 10.");
                return;
            }

            if (avaliacaoController.avaliarEntrega(idAvaliacao, matriculaAluno, nota)) {
                exibirInfo("Nota atribuída com sucesso.");
            } else {
                exibirErro("Nota não pôde ser atribuída.");
            }
        } catch (Exception e) {
            exibirErro("Valor inválido!");
        }
    }
}
