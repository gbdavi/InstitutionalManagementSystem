package org.instituicao.entity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProfessorEntity extends FuncionarioEntity {
    private HashSet<TurmaEntity> turmaEntities = new HashSet<>();

    public ProfessorEntity(String cpf, String nome, LocalDate dataNascimento, String email, InstituicaoEntity instituicaoEntity) {
        super(cpf, nome, dataNascimento, email, instituicaoEntity);
    }

    /**
     * Turmas que o professor foi adicionado.
     * @return set não modificável de turmas.
     */
    public Set<TurmaEntity> getTurmas() {
        return Collections.unmodifiableSet(turmaEntities);
    }

    /**
     * Adiciona a turma no set de turmas caso o professor já tenha sido adicionado no set de professores da turma.
     */
    public boolean adicionarTurma(TurmaEntity turmaEntity) {
        if (turmaEntity.getProfessores().stream().noneMatch(professor -> professor == this)) {
            return false;
        }
        return turmaEntities.add(turmaEntity);
    }

    /**
     * Remove a turma do set de turmas caso o professor já tenha sido removido do set de professores da turma.
     */
    public boolean removerTurma(TurmaEntity turmaEntity) {
        if (turmaEntity.getProfessores().stream().anyMatch(professor -> professor == this)) {
            return false;
        }
        return turmaEntities.remove(turmaEntity);
    }
}
