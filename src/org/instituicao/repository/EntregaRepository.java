package org.instituicao.repository;

import org.instituicao.entity.EntregaEntity;
import org.instituicao.type.StatusEntrega;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class EntregaRepository {
    private static HashSet<EntregaEntity> entregaEntities = new HashSet<>();

    /**
     * Adiciona entrega ao reposit�rio local.
     */
    public boolean adicionarEntrega(EntregaEntity entregaEntity) {
        return entregaEntity != null && entregaEntities.add(entregaEntity);
    }

    /**
     * Remove entrega do reposit�rio local.
     */
    public boolean removerEntrega(EntregaEntity entregaEntity) {
        return entregaEntities.remove(entregaEntity);
    }

    /**
     * Busca entregas pertencentes ao aluno na turma no reposit�rio local atrav�s do id da turma e matr�cula do aluno.
     */
    public List<EntregaEntity> getEntregasByTurmaAluno(int turmaId, int matriculaAluno) {
        return entregaEntities.stream()
                .filter(entregaEntity -> entregaEntity.getAvaliacao().getTurma().getId() == turmaId && entregaEntity.getAluno().getMatricula() == matriculaAluno)
                .toList();
    }

    /**
     * Busca entrega no reposit�rio local atrav�s do id da avalia��o e matr�cula do aluno.
     */
    public Optional<EntregaEntity> getEntregaByAvaliacaoAluno(int avaliacaoId, int matriculaAluno) {
        return entregaEntities.stream()
                .filter(entregaEntity -> entregaEntity.getAvaliacao().getId() == avaliacaoId && entregaEntity.getAluno().getMatricula() == matriculaAluno)
                .findFirst();
    }

    /**
     * Busca entregas pendentes do aluno no reposit�rio local atrav�s da matr�cula do aluno.
     */
    public List<EntregaEntity> getEntregasPendentesByAluno(int matriculaAluno) {
        return entregaEntities.stream()
                .filter(entregaEntity -> entregaEntity.getStatus() == StatusEntrega.PENDENTE && entregaEntity.getAluno().getMatricula() == matriculaAluno)
                .toList();
    }

    /**
     * Busca entregas pendentes de avalia��o pelo professor no reposit�rio local atrav�s da matr�cula do professor.
     */
    public List<EntregaEntity> getEntregasPendentesByProfessor(int matriculaProfessor) {
        return entregaEntities.stream()
                .filter(entregaEntity ->
                        entregaEntity.getStatus() == StatusEntrega.ENTREGUE &&
                        entregaEntity.getAvaliacao().getTurma().getProfessores().stream().anyMatch(professorEntity -> professorEntity.getMatricula() == matriculaProfessor))
                .toList();
    }
}
