package org.instituicao.controller;

import org.instituicao.dto.AvaliacaoDTO;
import org.instituicao.dto.AvaliacaoCadastroDTO;
import org.instituicao.service.AvaliacaoService;

public class AvaliacaoController {
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController() {
        this.avaliacaoService = new AvaliacaoService();
    }

    /**
     * Cadastra nova avaliação na base de dados.
     * Equivalente a rota /avaliacoes/cadastrar.
     * @param avaliacaoCadastroDTO dados de cadastro da avaliação.
     * @return dados da avaliação se cadastrada com sucesso, caso contrário null.
     */
    public AvaliacaoDTO cadastrar(AvaliacaoCadastroDTO avaliacaoCadastroDTO) {
        return avaliacaoService.cadastrar(avaliacaoCadastroDTO);
    }

    /**
     * Envia a resposta do aluno para a avaliação e define como entregue.
     * Equivalente a rota /avaliacoes/{idAvaliacao}/entregar
     * @param idAvaliacao id da avaliação.
     * @param matriculaAluno matrícula do aluno.
     * @param resposta resposta do aluno.
     */
    public boolean entregarAvaliacao(int idAvaliacao, int matriculaAluno, String resposta) {
        return avaliacaoService.entregarAvaliacao(idAvaliacao, matriculaAluno, resposta);
    }

    /**
     * Atribui nota para a avaliação entregue pelo aluno.
     * Equivalente a rota /avaliacoes/{idAvaliacao}/avaliar
     * @param idAvaliacao id da avaliação.
     * @param matriculaAluno id do aluno.
     * @param nota nota.
     */
    public boolean avaliarEntrega(int idAvaliacao, int matriculaAluno, float nota) {
        return avaliacaoService.avaliarEntrega(idAvaliacao, matriculaAluno, nota);
    }
}
