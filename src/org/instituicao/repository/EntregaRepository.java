package org.instituicao.repository;

import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.EntregaEntity;
import org.instituicao.entity.TurmaEntity;

import java.util.HashSet;
import java.util.List;

public class EntregaRepository {
    private static HashSet<EntregaEntity> entregaEntities = new HashSet<>();

    public boolean adicionarEntrega(EntregaEntity entregaEntity) {
        return entregaEntity != null && entregaEntities.add(entregaEntity);
    }

    public boolean removerEntrega(EntregaEntity entregaEntity) {
        return entregaEntities.remove(entregaEntity);
    }

    public List<EntregaEntity> getEntregasByTurmaAluno(TurmaEntity turmaEntity, AlunoEntity alunoEntity) {
        return entregaEntities.stream().filter(entregaEntity -> entregaEntity.getAvaliacao().getTurma().equals(turmaEntity) && entregaEntity.getAluno().equals(alunoEntity)).toList();
    }
}
