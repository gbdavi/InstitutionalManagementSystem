package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.EntregaEntity;
import org.instituicao.entity.TurmaEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class EntregaRepository {
    private static HashSet<EntregaEntity> entregaEntities = new HashSet<>();

    public boolean adicionarEntrega(EntregaEntity entregaEntity) {
        return entregaEntity != null && entregaEntities.add(entregaEntity);
    }

    public boolean removerEntrega(EntregaEntity entregaEntity) {
        return entregaEntities.remove(entregaEntity);
    }

    public List<EntregaEntity> getEntregasByTurmaAluno(int turmaId, int matriculaAluno) {
        return entregaEntities.stream().filter(entregaEntity -> entregaEntity.getAvaliacao().getTurma().getId() == turmaId && entregaEntity.getAluno().getMatricula() == matriculaAluno).toList();
    }
    public Optional<EntregaEntity> getEntregaByAvaliacaoAluno(int avaliacaoId, int matriculaAluno) {
        return entregaEntities.stream().filter(entregaEntity -> entregaEntity.getAvaliacao().getId() == avaliacaoId && entregaEntity.getAluno().getMatricula() == matriculaAluno).findFirst();
    }
}
