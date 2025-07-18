package org.instituicao.controller;

import org.instituicao.dto.TurmaCadastroDTO;
import org.instituicao.dto.TurmaDTO;
import org.instituicao.service.TurmaService;
import org.instituicao.type.StatusTurma;

import java.util.List;

public class TurmaController {
    private final TurmaService turmaService;

    public TurmaController() {
        this.turmaService = new TurmaService();
    }

    /**
     * Cadastra nova turma no banco de dados.
     * Equivalente a rota /turmas/cadastrar.
     * @param turmaCadastroDTO dados de cadastro da turma.
     * @return dados da turma se cadastrada com sucesso, caso contrário null.
     */
    public TurmaDTO cadastrar(TurmaCadastroDTO turmaCadastroDTO) {
        return turmaService.cadastrar(turmaCadastroDTO);
    }

    /**
     * Adiciona aluno na turma.
     * Equivalente a rota /turmas/{idTurma}/adicionar-aluno.
     * @param idTurma id da turma.
     * @param matriculaAluno matrícula do aluno.
     */
    public boolean adicionarAluno(int idTurma, int matriculaAluno) {
        return turmaService.adicionarAluno(idTurma, matriculaAluno);
    }

    /**
     * Adiciona professor na turma.
     * Equivalente a rota /turmas/{idTurma}/adicionar-professor.
     * @param idTurma id da turma.
     * @param matriculaProfessor matrícula do professor.
     */
    public boolean adicionarProfessor(int idTurma, int matriculaProfessor) {
        return turmaService.adicionarProfessor(idTurma, matriculaProfessor);
    }

    /**
     * Altera o status da turma.
     * Equivalente a rota /turmas/{idTurma}/alterar-status.
     * @param idTurma id da turma.
     * @param statusTurma novo status.
     */
    public boolean alterarStatus(int idTurma, StatusTurma statusTurma) {
        return turmaService.alterarStatus(idTurma, statusTurma);
    }

    /**
     * Retorna todas as turmas no banco de dados disponíveis para cadastro do aluno.
     * Equivalente a rota /turmas (disponivel, byAluno via queryParams).
     */
    public List<TurmaDTO> getTurmasDisponiveisByAluno(int matriculaAluno) {
        return turmaService.getTurmasDisponiveisByAluno(matriculaAluno);
    }

    /**
     * Retorna todas as turmas da instituição no banco de dados.
     * Equivalente a rota /turmas (byInstituicao via queryParams).
     */
    public List<TurmaDTO> getTurmasByInstituicao(int idInstituicao) {
        return turmaService.getTurmasByInstituicao(idInstituicao);
    }

    /**
     * Retorna as turmas ministradas pelo professor no banco de dados.
     * Equivalente a rota /turmas (byProfessor via queryParams).
     */
    public List<TurmaDTO> getTurmasByProfessor(int matriculaProfessor) {
        return turmaService.getTurmasByProfessor(matriculaProfessor);
    }
}
