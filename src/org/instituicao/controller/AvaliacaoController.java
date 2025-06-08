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
}
