package org.instituicao.service;

import org.instituicao.dto.TurmaCadastroDTO;
import org.instituicao.dto.TurmaDTO;
import org.instituicao.entity.*;
import org.instituicao.repository.*;
import org.instituicao.type.StatusTurma;

import java.util.Optional;

public class TurmaService {
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final EntregaRepository entregaRepository;

    public TurmaService() {
        this.turmaRepository = new TurmaRepository();
        this.alunoRepository = new AlunoRepository();
        this.funcionarioRepository = new FuncionarioRepository();
        this.disciplinaRepository = new DisciplinaRepository();
        this.entregaRepository = new EntregaRepository();
    }

    /**
     * Cadastra nova turma na base de dados.
     * @param turmaCadastroDTO dados da turma.
     * @return dados da turma se cadastrada com sucesso, caso contr�rio null.
     */
    public TurmaDTO cadastrar(TurmaCadastroDTO turmaCadastroDTO) {
        Optional<DisciplinaEntity> disciplina = disciplinaRepository.getDisciplinaById(turmaCadastroDTO.getIdDisciplina());
        if (disciplina.isPresent()) {
            TurmaEntity turma = new TurmaEntity(disciplina.get());
            if (turmaRepository.adicionarTurma(turma)) {
                return new TurmaDTO(turma);
            }
        }
        return null;
    }

    /**
     * Adiciona aluno na turma.
     * @param idTurma id da turma.
     * @param matriculaAluno matr�cula do aluno.
     */
    public boolean adicionarAluno(int idTurma, int matriculaAluno) {
        Optional<TurmaEntity> turma = turmaRepository.getTurmaById(idTurma);
        Optional<AlunoEntity> aluno = alunoRepository.getAlunoByMatricula(matriculaAluno);

        if (turma.isPresent() && aluno.isPresent()) {
            if (turma.get().adicionarAluno(aluno.get())) {
                for (AvaliacaoEntity avaliacaoEntity : turma.get().getAvaliacoes()) {
                    entregaRepository.adicionarEntrega(new EntregaEntity(aluno.get(), avaliacaoEntity));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Adiciona professor na turma.
     * @param idTurma id da turma.
     * @param matriculaProfessor matr�cula do professor.
     */
    public boolean adicionarProfessor(int idTurma, int matriculaProfessor) {
        Optional<TurmaEntity> turma = turmaRepository.getTurmaById(idTurma);
        Optional<FuncionarioEntity> professor = funcionarioRepository.getFuncionarioByMatricula(matriculaProfessor);

        if (turma.isPresent() && professor.isPresent()) {
            if (professor.get() instanceof ProfessorEntity) {
                return turma.get().adicionarProfessor((ProfessorEntity) professor.get());
            }
        }
        return false;
    }

    /**
     * Altera o status da turma.
     * @param idTurma id da turma.
     * @param statusTurma novo status.
     */
    public boolean alterarStatus(int idTurma, StatusTurma statusTurma) {
        Optional<TurmaEntity> turma = turmaRepository.getTurmaById(idTurma);
        if (turma.isPresent()) {
            turma.get().setStatus(statusTurma);
            return true;
        }
        return false;
    }
}
