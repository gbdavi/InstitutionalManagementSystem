package org.instituicao.controller;

import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.service.ProfessorService;

/**
 * Controller para dados do professor
 * Equivalente a rota base /professores
 */
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController() {
        this.professorService = new ProfessorService();
    }

    /**
     * Retorna informacoes do professor caso login realizado com sucesso
     * Equivalente a rota /professores/login
     * @param emailCorporativo email corporativo do professor.
     * @param senha senha do professor.
     * @return
     */
    public FuncionarioDTO login(String emailCorporativo, String senha) {
        if (emailCorporativo == null || senha == null || emailCorporativo.length() == 0 || senha.length() == 0) {
            return null;
        }
        return professorService.login(emailCorporativo, senha);
    }
}
