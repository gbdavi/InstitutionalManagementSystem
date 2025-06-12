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
     * Tela para entrega de avalia��o.
     * @param matriculaAluno matr�cula do aluno que ir� entregar a avalia��o.
     */
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

    /**
     * Tela para professor avaliar entrega.
     * @param matriculaProfessor matr�cula do professor que ir� avaliar.
     */
    public void telaAvaliarEntrega(int matriculaProfessor) {
        try {
            exibirCabecalho("Avaliar entregas");
            exibirInfo("\n", "Avalia��es dispon�veis:");
            List<EntregaDTO> entregasPendentes = entregaController.getEntregasPendentesByProfessor(matriculaProfessor);
            List<AvaliacaoDTO> avaliacoesDisponiveis = entregasPendentes.stream()
                    .map(EntregaDTO::getAvaliacao)
                    .collect(Collectors.toCollection(HashSet::new)).stream()
                    .sorted(Comparator.comparing(AvaliacaoDTO::getDescricao))
                    .toList();
            avaliacoesDisponiveis.forEach(System.out::println);

            int idAvaliacao = Integer.parseInt(solicitarCampo("\nId avalia��o"));
            if (avaliacoesDisponiveis.stream().noneMatch(avaliacaoDTO -> avaliacaoDTO.getId() == idAvaliacao)) {
                exibirErro("Avalia��o inv�lida!");
                return;
            }

            exibirInfo("\n", "Alunos dispon�veis:");
            List<AlunoDTO> alunosDisponiveis = entregasPendentes.stream()
                    .filter(entregaDTO -> entregaDTO.getAvaliacao().getId() == idAvaliacao)
                    .map(EntregaDTO::getAluno)
                    .toList();
            alunosDisponiveis.forEach(System.out::println);

            int matriculaAluno = Integer.parseInt(solicitarCampo("\nMatr�cula aluno"));
            Optional<EntregaDTO> entregaAluno = entregasPendentes.stream()
                    .filter(entregaDTO -> entregaDTO.getAluno().getMatricula() == matriculaAluno && entregaDTO.getAvaliacao().getId() == idAvaliacao)
                    .findFirst();
            if (entregaAluno.isEmpty()) {
                exibirErro("Aluno inv�lido!");
                return;
            }

            System.out.println("\nResposta do aluno: " + entregaAluno.get().getResposta());

            float nota = Float.parseFloat(solicitarCampo("\nNota").replace(",", "."));
            if (nota < 0 || nota > 10) {
                exibirErro("A nota deve ser de 0 a 10.");
                return;
            }

            if (avaliacaoController.avaliarEntrega(idAvaliacao, matriculaAluno, nota)) {
                exibirInfo("Nota atribu�da com sucesso.");
            } else {
                exibirErro("Nota n�o p�de ser atribu�da.");
            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }

    /**
     * Tela para professor cadastrar avalia��o na turma
     * @param matriculaProfessor matr�cula do professor que far� o cadastro.
     */
    public void telaCadastrarAvaliacao(int matriculaProfessor) {
        try {
            exibirCabecalho("Cadastrar avalia��o");
            exibirInfo("\n", "Turmas dispon�veis");
            List<TurmaDTO> turmasDisponiveis = turmaController.getTurmasByProfessor(matriculaProfessor);
            turmasDisponiveis.forEach(System.out::println);

            int idTurma = Integer.parseInt(solicitarCampo("\nId turma"));
            if (turmasDisponiveis.stream().noneMatch(avaliacaoDTO -> avaliacaoDTO.getId() == idTurma)) {
                exibirErro("Turma inv�lida!");
                return;
            }

            String descricao = solicitarCampo("Descri��o");
            if (descricao.isBlank()) {
                exibirErro("Descri��o inv�lida!");
                return;
            }

            AvaliacaoCadastroDTO avaliacaoCadastroDTO = new AvaliacaoCadastroDTO(descricao, idTurma);
            AvaliacaoDTO avaliacaoDTO = avaliacaoController.cadastrar(avaliacaoCadastroDTO);
            if (avaliacaoDTO != null) {
                exibirInfo("\n", "Avalia��o cadastrada com sucesso.");
                System.out.println(avaliacaoDTO);
            } else {
                exibirErro("Avalia��o n�o p�de ser cadastrada.");
            }
        } catch (Exception e) {
            exibirErro("Valor inv�lido!");
        }
    }
}
