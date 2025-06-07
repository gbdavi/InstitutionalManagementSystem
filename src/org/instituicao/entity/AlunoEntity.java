package org.instituicao.entity;

import org.instituicao.util.security.SenhaUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class AlunoEntity extends PessoaEntity {

    private int matricula;
    private String emailAcademico;
    private String senhaHash;
    private HashSet<CursoEntity> cursoEntities = new HashSet<>();
    private HashSet<TurmaEntity> turmaEntities = new HashSet<>();
    private final InstituicaoEntity instituicaoEntity;

    public AlunoEntity(String cpf, String nome, LocalDate dataNascimento, String email, InstituicaoEntity instituicaoEntity) {
        super(cpf, nome, dataNascimento, email);
        if (instituicaoEntity == null) {
            throw new NullPointerException("O parâmetro 'instituicaoEntity' não pode ser nulo");
        }
        this.instituicaoEntity = instituicaoEntity;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getEmailAcademico() {
        return emailAcademico;
    }

    public void setEmailAcademico(String emailAcademico) {
        this.emailAcademico = emailAcademico;
    }

    public boolean verificarSenha(String senha) {
        return senha != null && senhaHash != null && SenhaUtils.verificarSenha(senha, senhaHash);
    }

    public void setSenha(String senha) {
        this.senhaHash = SenhaUtils.gerarHashSenha(senha);
    }

    public Set<CursoEntity> getCursos() {
        return Collections.unmodifiableSet(cursoEntities);
    }

    public boolean adicionarCurso(CursoEntity cursoEntity) {
        return cursoEntities.add(cursoEntity);
    }

    public boolean removerCurso(CursoEntity cursoEntity) {
        return cursoEntities.remove(cursoEntity);
    }

    public Set<TurmaEntity> getTurmas() {
        return Collections.unmodifiableSet(turmaEntities);
    }

    /**
     * Adiciona a turma no set de turmas caso o aluno já tenha sido adicionado no set de alunos da turma.
     * @param turmaEntity
     */
    public boolean adicionarTurma(TurmaEntity turmaEntity) {
        if (
            turmaEntity.getAlunos().stream().noneMatch(aluno -> aluno == this) ||
            cursoEntities.stream().noneMatch(cursoEntity -> cursoEntity.getDisciplinas().contains(turmaEntity.getDisciplina()))
        ) {
            return false;
        }
        return turmaEntities.add(turmaEntity);
    }

    /**
     * Remove a turma do set de turmas caso o aluno já tenha sido removido do set de alunos da turma.
     * @param turmaEntity
     */
    public boolean removerTurma(TurmaEntity turmaEntity) {
        if (turmaEntity.getAlunos().stream().anyMatch(aluno -> aluno == this)) {
            return false;
        }
        return turmaEntities.remove(turmaEntity);
    }

    public InstituicaoEntity getInstituicaoEntity() {
        return instituicaoEntity;
    }

    @Override
    public String toString() {
        return getMatricula() + " - " + getNome() + " - " + getEmailAcademico();
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
