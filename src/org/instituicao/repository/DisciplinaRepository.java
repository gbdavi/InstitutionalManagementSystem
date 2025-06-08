package org.instituicao.repository;

import org.instituicao.entity.DisciplinaEntity;
import org.instituicao.entity.InstituicaoEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class DisciplinaRepository {
    private static HashSet<DisciplinaEntity> disciplinaEntities = new HashSet<>();

    public boolean adicionarDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntity != null && disciplinaEntities.add(disciplinaEntity);
    }

    public boolean removerDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntities.remove(disciplinaEntity);
    }

    public Optional<DisciplinaEntity> getDisciplinaById(int id) {
        return disciplinaEntities.stream().filter(disciplinaEntity -> disciplinaEntity.getId() == id).findFirst();
    }

    public List<DisciplinaEntity> getDisciplinasByInstituicao(InstituicaoEntity instituicaoEntity) {
        return disciplinaEntities.stream().filter(curso -> curso.getInstituicaoEntity().equals(instituicaoEntity)).toList();
    }
}
