package org.instituicao.repository;

import org.instituicao.entity.InstituicaoEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InstituicaoRepository {
    private static HashSet<InstituicaoEntity> instituicaoEntities = new HashSet<>();

    /**
     * Adiciona institui��o ao reposit�rio local.
     */
    public boolean adicionarInstituicao(InstituicaoEntity instituicaoEntity) {
        return instituicaoEntity != null && instituicaoEntities.add(instituicaoEntity);
    }

    /**
     * Remove institui��o do reposit�rio local.
     */
    public boolean removerInstituicao(InstituicaoEntity instituicaoEntity) {
        return instituicaoEntities.remove(instituicaoEntity);
    }

    /**
     * Busca todas as institui��es cadastradas no reposit�rio local.
     * @return set n�o modific�vel de institui��es.
     */
    public Set<InstituicaoEntity> getInstituicoes() {
        return Collections.unmodifiableSet(instituicaoEntities);
    }

    /**
     * Busca institui��o no reposit�rio local atrav�s do id.
     */
    public Optional<InstituicaoEntity> getInstituicaoById(int id) {
        return instituicaoEntities.stream()
                .filter(instituicaoEntity -> instituicaoEntity.getId() == id)
                .findFirst();
    }

}
