package org.instituicao.controller;

import org.instituicao.dto.*;
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
     * @return dados do aluno se cadastrado com sucesso, caso contr�rio null.
     */
    public AlunoDTO cadastrar(AlunoCadastroDTO alunoCadastroDTO) {
        return alunoService.cadastrar(alunoCadastroDTO);
    }

    /**
     * Relat�rio completo de desempenho do aluno em disciplinas j� iniciadas ou conclu�das, agrupadas por curso e ordenadas pelo nome da disciplina.
     * Equivalente a rota /alunos/{matriculaAluno}/relatorio
     * @param matriculaAluno matr�cula do aluno.
     * @return dados do relat�rio se aluno existir, caso contr�rio null.
     */
    public RelatorioAlunoDTO getRelatorio(int matriculaAluno) {
        return alunoService.getRelatorio(matriculaAluno);
    }

    /**
     * Retorna todos os alunos da instituicao cadastrados na base de dados.
     * Equivalente a rota /alunos.
     */
    public List<AlunoDTO> getAlunos(int idInstituicao) {
        return alunoService.getAlunos(idInstituicao);
    }

    /**
     * Atualiza os dados pessoais do aluno.
     * Equivalente a rota /alunos/{matriculaAluno}/alterar-dados.
     * @param matricula matr�cula do aluno.
     * @param dadosPessoaisAlterados dados a serem atualizados.
     */
    public boolean alterarDadosPessoais(int matricula, PessoaDTO dadosAlterados) {
        return this.alunoService.alterarDadosPessoais(matricula, dadosAlterados);
    }

    /**
     * Retorna dados do aluno cadastrado na base de dados.
     * Equivalente a rota /alunos/{matriculaAluno}
     * @param matriculaAluno matr�cula do aluno.
     */
    public AlunoDTO getAluno(int matriculaAluno) {
        return this.alunoService.getAluno(matriculaAluno);
    }
}
