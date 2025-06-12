package org.instituicao.controller;

import org.instituicao.dto.CursoCadastroDTO;
import org.instituicao.dto.CursoDTO;
import org.instituicao.repository.CursoService;

import java.util.List;

public class CursoController {
    private final CursoService cursoService;

    public CursoController() {
        this.cursoService = new CursoService();
    }

    /**
     * Cadastra novo curso no banco de dados.
     * Equivalente a rota /cursos/cadastrar.
     * @param cursoCadastroDTO dados de cadastro do curso.
     * @return dados do curso se cadastrado com sucesso, caso contrário null.
     */
    public CursoDTO cadastrar(CursoCadastroDTO cursoCadastroDTO) {
        return cursoService.cadastrar(cursoCadastroDTO);
    }

    /**
     * Adiciona aluno no curso.
     * Equivalente a rota /cursos/{idCurso}/adicionar-aluno.
     * @param idCurso id do curso.
     * @param matriculaAluno matrícula do aluno.
     */
    public boolean adicionarAluno(int idCurso, int matriculaAluno) {
        return cursoService.adicionarAluno(idCurso, matriculaAluno);
    }

    /**
     * Adiciona disciplina ao curso.
     * Equivalente a rota /cursos/{idCurso}/adicionar-disciplina.
     * @param idCurso id do curso.
     * @param idDisciplina id da disciplina.
     */
    public boolean adicionarDisciplina(int idCurso, int idDisciplina) {
        return cursoService.adicionarDisciplina(idCurso, idDisciplina);
    }

    /**
     * Retorna todos os cursos da instituicao cadastrados no banco de dados.
     * Equivalente a rota /cursos.
     */
    public List<CursoDTO> getCursos(int idInstituicao) {
        return cursoService.getCursos(idInstituicao);
    }
}
