package org.instituicao.controller;

import org.instituicao.dto.AlunoCadastroDTO;
import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.RelatorioAlunoDTO;
import org.instituicao.dto.RelatorioCursoDTO;
import org.instituicao.service.AlunoService;

import java.util.List;

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
     * Equivalente a rota /alunos/login.
     * @param emailAcademico email academico do aluno.
     * @param senha senha do aluno.
     */
    public AlunoDTO login(String emailAcademico, String senha) {
        if (emailAcademico == null || senha == null || emailAcademico.length() == 0 || senha.length() == 0) {
            return null;
        }
        return alunoService.login(emailAcademico, senha);
    }

    /**
     * Cadastra novo aluno na base de dados.
     * Equivalente a rota /alunos/cadastrar.
     * @param alunoCadastroDTO dados de cadastro do aluno.
     * @return dados do aluno se cadastrado com sucesso, caso contrário null.
     */
    public AlunoDTO cadastrar(AlunoCadastroDTO alunoCadastroDTO) {
        return alunoService.cadastrar(alunoCadastroDTO);
    }

    public RelatorioAlunoDTO getRelatorio(int matriculaAluno) {
        return alunoService.getRelatorio(matriculaAluno);
    }

}
