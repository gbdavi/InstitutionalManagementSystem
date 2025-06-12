package org.instituicao.repository;

import org.instituicao.entity.CursoEntity;
import org.instituicao.entity.InstituicaoEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class CursoRepository {
    private static HashSet<CursoEntity> cursoEntities = new HashSet<>();

    /**
     * Adiciona curso ao repositório local.
     */
    public boolean adicionarCurso(CursoEntity cursoEntity) {
        return cursoEntity != null && cursoEntities.add(cursoEntity);
    }

    /**
     * Remove curso do repositório local.
     */
    public boolean removerCurso(CursoEntity cursoEntity) {
        return cursoEntities.remove(cursoEntity);
    }

    /**
     * Busca cursos no repositório local através do id da instituição.
     */
    public List<CursoEntity> getCursosByInstituicao(int idInstituicao) {
        return cursoEntities.stream()
                .filter(curso -> curso.getInstituicaoEntity().getId() == idInstituicao)
                .toList();
    }

    /**
     * Busca curso no repositório local através do id.
     */
    public Optional<CursoEntity> getCursoById(int idCurso) {
        return cursoEntities.stream()
                .filter(curso -> curso.getId() == idCurso)
                .findFirst();
    }
}
