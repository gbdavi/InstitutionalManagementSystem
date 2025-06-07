package org.instituicao.controller;

import org.instituicao.dto.AlunoDTO;
import org.instituicao.service.AlunoService;

/**
 * Controller para dados do aluno
 * Equivalente a rota base /alunos
 */
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController() {
        this.alunoService = new AlunoService();
    }

    /**
     * Retorna informacoes do aluno caso login realizado com sucesso
     * Equivalente a rota /alunos/login
     * @param emailAcademico email academico do aluno.
     * @param senha senha do aluno.
     * @return
     */
    public AlunoDTO login(String emailAcademico, String senha) {
        if (emailAcademico == null || senha == null || emailAcademico.length() == 0 || senha.length() == 0) {
            return null;
        }
        return alunoService.login(emailAcademico, senha);
    }

}
