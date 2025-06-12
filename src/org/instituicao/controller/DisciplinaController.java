package org.instituicao.controller;

import org.instituicao.dto.DisciplinaCadastroDTO;
import org.instituicao.dto.DisciplinaDTO;
import org.instituicao.service.DisciplinaService;

public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    public DisciplinaController() {
        this.disciplinaService = new DisciplinaService();
    }

    /**
     * Cadastra nova disciplina no banco de dados.
     * Equivalente a rota /disciplinas/cadastrar.
     * @param disciplinaCadastroDTO dados de cadastro da disciplina.
     * @return dados da disciplina se cadastrada com sucesso, caso contrário null.
     */
    public DisciplinaDTO cadastrar(DisciplinaCadastroDTO disciplinaCadastroDTO) {
        return disciplinaService.cadastrar(disciplinaCadastroDTO);
    }
}
