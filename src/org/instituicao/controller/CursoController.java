package org.instituicao.controller;

import org.instituicao.dto.CursoCadastroDTO;
import org.instituicao.dto.CursoDTO;
import org.instituicao.repository.CursoService;

public class CursoController {
    private final CursoService cursoService;

    public CursoController() {
        this.cursoService = new CursoService();
    }

    /**
     * Cadastra novo curso na base de dados.
     * Equivalente a rota /cursos/cadastrar.
     * @param cursoCadastroDTO dados de cadastro do curso.
     * @return dados do curso se cadastrado com sucesso, caso contrário null.
     */
    public CursoDTO cadastrar(CursoCadastroDTO cursoCadastroDTO) {
        return cursoService.cadastrar(cursoCadastroDTO);
    }

    /**
     * Adiciona aluno no curso.
     * Equivalente a rota /cursos/{cursoId}/adicionar-aluno.
     * @param idCurso id do curso.
     * @param matriculaAluno matrícula do aluno.
     */
    public boolean adicionarAluno(int idCurso, int matriculaAluno) {
        return cursoService.adicionarAluno(idCurso, matriculaAluno);
    }

    /**
     * Adiciona disciplina ao curso.
     * Equivalente a rota /cursos/{cursoId}/adicionar-disciplina.
     * @param idCurso id do curso.
     * @param idDisciplina id da disciplina.
     */
    public boolean adicionarDisciplina(int idCurso, int idDisciplina) {
        return cursoService.adicionarDisciplina(idCurso, idDisciplina);
    }
}
