package org.instituicao.repository;

import org.instituicao.entity.AvaliacaoEntity;
import org.instituicao.entity.TurmaEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class AvaliacaoRepository {
    private static HashSet<AvaliacaoEntity> avaliacaoEntities = new HashSet<>();

    /**
     * Adiciona avalia��o ao reposit�rio local.
     */
    public boolean adicionarAvaliacao(AvaliacaoEntity avaliacaoEntity) {
        return avaliacaoEntity != null && avaliacaoEntities.add(avaliacaoEntity);
    }

    /**
     * Remove avalia��o do reposit�rio local.
     */
    public boolean removerAvaliacao(AvaliacaoEntity avaliacaoEntity) {
        return avaliacaoEntities.remove(avaliacaoEntity);
    }
}
