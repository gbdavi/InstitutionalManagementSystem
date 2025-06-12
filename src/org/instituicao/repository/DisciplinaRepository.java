package org.instituicao.repository;

import org.instituicao.entity.DisciplinaEntity;
import org.instituicao.entity.InstituicaoEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class DisciplinaRepository {
    private static HashSet<DisciplinaEntity> disciplinaEntities = new HashSet<>();

    /**
     * Adiciona disciplina ao repositório local.
     */
    public boolean adicionarDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntity != null && disciplinaEntities.add(disciplinaEntity);
    }

    /**
     * Remove disciplina do repositório local.
     */
    public boolean removerDisciplina(DisciplinaEntity disciplinaEntity) {
        return disciplinaEntities.remove(disciplinaEntity);
    }

    /**
     * Busca disciplina no repositório local através do id.
     */
    public Optional<DisciplinaEntity> getDisciplinaById(int id) {
        return disciplinaEntities.stream()
                .filter(disciplinaEntity -> disciplinaEntity.getId() == id)
                .findFirst();
    }
}
