package org.instituicao.repository;

import org.instituicao.entity.DisciplinaEntity;
import org.instituicao.entity.InstituicaoEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class DisciplinaRepository {
    private static HashSet<DisciplinaEntity> disciplinaEntities = new HashSet<>();

    /**
     * Adiciona disciplina ao reposit�rio local.
     */
    public boolean adicionarDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntity != null && disciplinaEntities.add(disciplinaEntity);
    }

    /**
     * Remove disciplina do reposit�rio local.
     */
    public boolean removerDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntities.remove(disciplinaEntity);
    }

    /**
     * Busca disciplina no reposit�rio local atrav�s do id.
     */
    public Optional<DisciplinaEntity> getDisciplinaById(int id) {
        return disciplinaEntities.stream()
                .filter(disciplinaEntity -> disciplinaEntity.getId() == id)
                .findFirst();
    }
}
