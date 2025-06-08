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
     * Cadastra nova avalia��o na base de dados.
     * Equivalente a rota /avaliacoes/cadastrar.
     * @param avaliacaoCadastroDTO dados de cadastro da avalia��o.
     * @return dados da avalia��o se cadastrada com sucesso, caso contr�rio null.
     */
    public AvaliacaoDTO cadastrar(AvaliacaoCadastroDTO avaliacaoCadastroDTO) {
        return avaliacaoService.cadastrar(avaliacaoCadastroDTO);
    }
}
