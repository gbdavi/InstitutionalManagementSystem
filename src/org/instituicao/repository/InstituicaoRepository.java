package org.instituicao.repository;

import org.instituicao.entity.InstituicaoEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InstituicaoRepository {
    private static HashSet<InstituicaoEntity> instituicaoEntities = new HashSet<>();

    /**
     * Adiciona instituição ao repositório local.
     */
    public boolean adicionarInstituicao(InstituicaoEntity instituicaoEntity) {
        return instituicaoEntity != null && instituicaoEntities.add(instituicaoEntity);
    }

    /**
     * Remove instituição do repositório local.
     */
    public boolean removerInstituicao(InstituicaoEntity instituicaoEntity) {
        return instituicaoEntities.remove(instituicaoEntity);
    }

    /**
     * Busca todas as instituições cadastradas no repositório local.
     * @return set não modificável de instituições.
     */
    public Set<InstituicaoEntity> getInstituicoes() {
        return Collections.unmodifiableSet(instituicaoEntities);
    }

    /**
     * Busca instituição no repositório local através do id.
     */
    public Optional<InstituicaoEntity> getInstituicaoById(int id) {
        return instituicaoEntities.stream()
                .filter(instituicaoEntity -> instituicaoEntity.getId() == id)
                .findFirst();
    }

}
