package org.instituicao.entity;

import org.instituicao.type.StatusTurma;
import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TurmaEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(1);

    private final int id;
    private final DisciplinaEntity disciplinaEntity;
    private HashSet<ProfessorEntity> professorEntities = new HashSet<>();
    private HashSet<AlunoEntity> alunoEntities = new HashSet<>();
    private HashSet<AvaliacaoEntity> avaliacaoEntities = new HashSet<>();
    private StatusTurma status = StatusTurma.NAO_INICIADA;

    public TurmaEntity(DisciplinaEntity disciplinaEntity) {
        id = idGenerator.gerarProximoId();
        this.disciplinaEntity = disciplinaEntity;
    }

    public int getId() {
        return id;
    }

    public StatusTurma getStatus() {
        return status;
    }

    public void setStatus(StatusTurma status) {
        this.status = status;
    }

    public DisciplinaEntity getDisciplina() {
        return disciplinaEntity;
    }

    /**
     * Professores adicionados � turma.
     * @return set n�o modific�vel de professores.
     */
    public Set<ProfessorEntity> getProfessores() {
        return Collections.unmodifiableSet(professorEntities);
    }

    /**
     * Adiciona o professor na turma e adiciona a turma no set de turmas do professor.
     */
    public boolean adicionarProfessor(ProfessorEntity professorEntity) {
        return professorEntity != null && professorEntities.add(professorEntity) && professorEntity.adicionarTurma(this);
    }

    /**
     * Remove o professor da turma e remove a turma do set de turmas do professor.
     */
    public boolean removerProfessor(ProfessorEntity professorEntity) {
        return professorEntities.remove(professorEntity) && professorEntity.removerTurma(this);
    }

    /**
     * Alunos adicionados � turma.
     * @return set n�o modific�vel de alunos.
     */
    public Set<AlunoEntity> getAlunos() {
        return Collections.unmodifiableSet(alunoEntities);
    }

    /**
     * Adiciona o aluno na turma e adiciona a turma no set de turmas do aluno.
     * @param alunoEntity
     */
    public boolean adicionarAluno(AlunoEntity alunoEntity) {
        if (
            alunoEntity == null ||
            alunoEntity.getCursos().stream().noneMatch(curso -> curso.getDisciplinas().contains(disciplinaEntity))
        ) {
            return false;
        }
        return alunoEntities.add(alunoEntity) && alunoEntity.adicionarTurma(this);
    }

    /**
     * Remove o aluno da turma e remove a turma do set de turmas do aluno.
     * @param alunoEntity
     */
    public boolean removerAluno(AlunoEntity alunoEntity) {
        return alunoEntities.remove(alunoEntity) && alunoEntity.removerTurma(this);
    }

    /**
     * Avalia��es cadastradas na turma.
     * @return set n�o modific�vel de avalia��es.
     */
    public Set<AvaliacaoEntity> getAvaliacoes() {
        return Collections.unmodifiableSet(avaliacaoEntities);
    }


    /**
     * Adiciona a avalia��o no set de avalia��es.
     */
    public boolean adicionarAvaliacao(AvaliacaoEntity avaliacaoEntity) {
        return avaliacaoEntities.add(avaliacaoEntity);
    }

    /**
     * Remove a avalia��o do set de avalia��es.
     */
    public boolean removerAvaliacao(AvaliacaoEntity avaliacaoEntity) {
        return avaliacaoEntities.remove(avaliacaoEntity);
    }

    @Override
    public String toString() {
        return id + " - " + disciplinaEntity;
    }

    /**
     * Gera o hashcode da inst�ncia.
     * Obs.: necess�rio para utiliza��o do HashSet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara a inst�ncia atual com outra fornecida.
     * Obs.: necess�rio para utiliza��o do HashSet.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurmaEntity other = (TurmaEntity) obj;
		return Objects.equals(id, other.getId());
    }
}
