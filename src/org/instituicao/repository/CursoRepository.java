package org.instituicao.repository;

import org.instituicao.entity.CursoEntity;
import org.instituicao.entity.InstituicaoEntity;

import java.util.HashSet;
import java.util.List;

public class CursoRepository {
    private static HashSet<CursoEntity> cursoEntities = new HashSet<>();

    public boolean adicionarCurso(CursoEntity cursoEntity) {
        return cursoEntity != null && cursoEntities.add(cursoEntity);
    }

    public boolean removerCurso(CursoEntity cursoEntity) {
        return cursoEntities.remove(cursoEntity);
    }

    public List<CursoEntity> getCursosByInstituicao(InstituicaoEntity instituicao) {
        return cursoEntities.stream().filter(curso -> curso.getInstituicaoEntity().equals(instituicao)).toList();
    }
}
