package org.instituicao.controller;

import org.instituicao.dto.FuncionarioCadastroDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.service.ProfessorService;

import java.util.List;

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
     * Equivalente a rota /professores/login.
     * @param emailCorporativo email corporativo do professor.
     * @param senha senha do professor.
     */
    public FuncionarioDTO login(String emailCorporativo, String senha) {
        if (emailCorporativo == null || senha == null || emailCorporativo.isBlank() || senha.isBlank()) {
            return null;
        }
        return professorService.login(emailCorporativo, senha);
    }

    /**
     * Cadastra novo professor no banco de dados.
     * Equivalente a rota /professores/cadastrar.
     * @param professorCadastroDTO dados de cadastro do professor.
     * @return dados do professor se cadastrado com sucesso, caso contrário null.
     */
    public FuncionarioDTO cadastrar(FuncionarioCadastroDTO professorCadastroDTO) {
        return professorService.cadastrar(professorCadastroDTO);
    }

    /**
     * Retorna todos os professores da instituicao cadastrados no banco de dados.
     * Equivalente a rota /professores.
     */
    public List<FuncionarioDTO> getProfessores(int idInstituicao) {
        return professorService.getProfessores(idInstituicao);
    }
}
