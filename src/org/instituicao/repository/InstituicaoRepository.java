package org.instituicao.repository;

import org.instituicao.entity.InstituicaoEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class InstituicaoRepository {
    private static HashSet<InstituicaoEntity> instituicaoEntities = new HashSet<>();

    public boolean adicionarInstituicao(InstituicaoEntity instituicaoEntity) {
        return instituicaoEntity != null && instituicaoEntities.add(instituicaoEntity);
    }

    public boolean removerInstituicao(InstituicaoEntity instituicaoEntity) {
        return instituicaoEntities.remove(instituicaoEntity);
    }

    public Set<InstituicaoEntity> getInstituicoes() {
        return Collections.unmodifiableSet(instituicaoEntities);
    }

    public InstituicaoEntity getInstituicaoById(int id) {
        return instituicaoEntities.stream().filter(instituicaoEntity -> instituicaoEntity.getId() == id).findFirst().orElse(null);
    }

}
