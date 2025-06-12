package org.instituicao.repository;

import org.instituicao.entity.EntregaEntity;
import org.instituicao.type.StatusEntrega;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class EntregaRepository {
    private static HashSet<EntregaEntity> entregaEntities = new HashSet<>();

    /**
     * Adiciona entrega ao repositório local.
     */
    public boolean adicionarEntrega(EntregaEntity entregaEntity) {
        return entregaEntity != null && entregaEntities.add(entregaEntity);
    }

    /**
     * Remove entrega do repositório local.
     */
    public boolean removerEntrega(EntregaEntity entregaEntity) {
        return entregaEntities.remove(entregaEntity);
    }

    /**
     * Busca entregas pertencentes ao aluno na turma no repositório local através do id da turma e matrícula do aluno.
     */
    public List<EntregaEntity> getEntregasByTurmaAluno(int turmaId, int matriculaAluno) {
        return entregaEntities.stream()
                .filter(entregaEntity -> entregaEntity.getAvaliacao().getTurma().getId() == turmaId && entregaEntity.getAluno().getMatricula() == matriculaAluno)
                .toList();
    }

    /**
     * Busca entrega no repositório local através do id da avaliação e matrícula do aluno.
     */
    public Optional<EntregaEntity> getEntregaByAvaliacaoAluno(int avaliacaoId, int matriculaAluno) {
        return entregaEntities.stream()
                .filter(entregaEntity -> entregaEntity.getAvaliacao().getId() == avaliacaoId && entregaEntity.getAluno().getMatricula() == matriculaAluno)
                .findFirst();
    }

    /**
     * Busca entregas pendentes do aluno no repositório local através da matrícula do aluno.
     */
    public List<EntregaEntity> getEntregasPendentesByAluno(int matriculaAluno) {
        return entregaEntities.stream()
                .filter(entregaEntity -> entregaEntity.getStatus() == StatusEntrega.PENDENTE && entregaEntity.getAluno().getMatricula() == matriculaAluno)
                .toList();
    }

    /**
     * Busca entregas pendentes de avaliação pelo professor no repositório local através da matrícula do professor.
     */
    public List<EntregaEntity> getEntregasPendentesByProfessor(int matriculaProfessor) {
        return entregaEntities.stream()
                .filter(entregaEntity ->
                        entregaEntity.getStatus() == StatusEntrega.ENTREGUE &&
                        entregaEntity.getAvaliacao().getTurma().getProfessores().stream().anyMatch(professorEntity -> professorEntity.getMatricula() == matriculaProfessor))
                .toList();
    }
}
