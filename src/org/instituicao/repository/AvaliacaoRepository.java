package org.instituicao.repository;

import org.instituicao.entity.AvaliacaoEntity;
import org.instituicao.entity.TurmaEntity;

import java.util.HashSet;
import java.util.List;

public class AvaliacaoRepository {
    private static HashSet<AvaliacaoEntity> avaliacaoEntities = new HashSet<>();

    public boolean adicionarAvaliacao(AvaliacaoEntity avaliacaoEntity) {
        return avaliacaoEntity != null && avaliacaoEntities.add(avaliacaoEntity);
    }

    public boolean removerAvaliacao(AvaliacaoEntity avaliacaoEntity) {
        return avaliacaoEntities.remove(avaliacaoEntity);
    }

    public List<AvaliacaoEntity> getAvaliacoesByTurma(TurmaEntity turmaEntity) {
        return avaliacaoEntities.stream().filter(avaliacaoEntity -> avaliacaoEntity.getTurma().equals(turmaEntity)).toList();
    }
}
