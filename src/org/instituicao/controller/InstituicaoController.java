package org.instituicao.controller;

import org.instituicao.dto.InstituicaoCadastroDTO;
import org.instituicao.dto.InstituicaoDTO;
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
     * Cadastra nova instituição na base de dados.
     * Equivalente a rota /instituicoes/cadastrar.
     * @param instituicaoCadastroDTO dados de cadastro da instituição.
     * @return dados da instituição se cadastrada com sucesso, caso contrário null.
     */
    public InstituicaoDTO cadastrar(InstituicaoCadastroDTO instituicaoCadastroDTO) {
        return instituicaoService.cadastrar(instituicaoCadastroDTO);
    }

    /**
     * Retorna todas as instituições cadastradas na base de dados.
     * Equivalente a rota /instituicoes.
     */
    public List<InstituicaoDTO> getInstituicoes() {
        return instituicaoService.getInstituicoes();
    }
}
