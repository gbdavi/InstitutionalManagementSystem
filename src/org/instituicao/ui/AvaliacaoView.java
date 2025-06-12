package org.instituicao.ui;

import org.instituicao.controller.AvaliacaoController;
import org.instituicao.controller.EntregaController;
import org.instituicao.controller.TurmaController;
import org.instituicao.dto.*;

import java.util.*;
import java.util.stream.Collectors;

public class AvaliacaoView extends BaseView {
    private final AvaliacaoController avaliacaoController;
    private final EntregaController entregaController;
    private final TurmaController turmaController;

    public AvaliacaoView() {
        this.avaliacaoController = new AvaliacaoController();
        this.entregaController =new EntregaController();
        this.turmaController = new TurmaController();
    }

    /**
     * Tela para entrega de avaliação.
     * @param matriculaAluno matrícula do aluno que irá entregar a avaliação.
     */
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

    /**
     * Tela para professor avaliar entrega.
     * @param matriculaProfessor matrícula do professor que irá avaliar.
     */
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
            if (avaliacoesDisponiveis.stream().noneMatch(avaliacaoDTO -> avaliacaoDTO.getId() == idAvaliacao)) {
                exibirErro("Avaliação inválida!");
                return;
            }

            exibirInfo("\n", "Alunos disponíveis:");
            List<AlunoDTO> alunosDisponiveis = entregasPendentes.stream()
                    .filter(entregaDTO -> entregaDTO.getAvaliacao().getId() == idAvaliacao)
                    .map(EntregaDTO::getAluno)
                    .toList();
            alunosDisponiveis.forEach(System.out::println);

            int matriculaAluno = Integer.parseInt(solicitarCampo("\nMatrícula aluno"));
            Optional<EntregaDTO> entregaAluno = entregasPendentes.stream()
                    .filter(entregaDTO -> entregaDTO.getAluno().getMatricula() == matriculaAluno && entregaDTO.getAvaliacao().getId() == idAvaliacao)
                    .findFirst();
            if (entregaAluno.isEmpty()) {
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

    /**
     * Tela para professor cadastrar avaliação na turma
     * @param matriculaProfessor matrícula do professor que fará o cadastro.
     */
    public void telaCadastrarAvaliacao(int matriculaProfessor) {
        try {
            exibirCabecalho("Cadastrar avaliação");
            exibirInfo("\n", "Turmas disponíveis");
            List<TurmaDTO> turmasDisponiveis = turmaController.getTurmasByProfessor(matriculaProfessor);
            turmasDisponiveis.forEach(System.out::println);

            int idTurma = Integer.parseInt(solicitarCampo("\nId turma"));
            if (turmasDisponiveis.stream().noneMatch(avaliacaoDTO -> avaliacaoDTO.getId() == idTurma)) {
                exibirErro("Turma inválida!");
                return;
            }

            String descricao = solicitarCampo("Descrição");
            if (descricao.isBlank()) {
                exibirErro("Descrição inválida!");
                return;
            }

            AvaliacaoCadastroDTO avaliacaoCadastroDTO = new AvaliacaoCadastroDTO(descricao, idTurma);
            AvaliacaoDTO avaliacaoDTO = avaliacaoController.cadastrar(avaliacaoCadastroDTO);
            if (avaliacaoDTO != null) {
                exibirInfo("\n", "Avaliação cadastrada com sucesso.");
                System.out.println(avaliacaoDTO);
            } else {
                exibirErro("Avaliação não pôde ser cadastrada.");
            }
        } catch (Exception e) {
            exibirErro("Valor inválido!");
        }
    }
}
