package org.instituicao.controller;

import org.instituicao.dto.InstituicaoDTO;
import org.instituicao.repository.InstituicaoRepository;
import org.instituicao.service.InstituicaoService;

import java.util.List;

/**
 * Controller para dados de instituicao.
 * Equivalente a rota base /instituicoes
 */
public class InstituicaoController {
    private final InstituicaoService instituicaoService;

    public InstituicaoController() {
        this.instituicaoService = new InstituicaoService();
    }

    /**
     * Retorna todas as instituições cadastradas na base de dados.
     * Equivalente a rota /instituicoes.
     */
    public List<InstituicaoDTO> getInstituicoes() {
        return instituicaoService.getInstituicoes();
    }
}
