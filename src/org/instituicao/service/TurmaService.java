package org.instituicao.service;

import org.instituicao.dto.TurmaCadastroDTO;
import org.instituicao.dto.TurmaDTO;
import org.instituicao.entity.*;
import org.instituicao.repository.*;
import org.instituicao.type.StatusTurma;

import java.util.List;
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
     * Cadastra nova turma no banco de dados.
     * @param turmaCadastroDTO dados da turma.
     * @return dados da turma se cadastrada com sucesso, caso contrário null.
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
     * Adiciona aluno na turma e atribui a ele as entregas das avaliações já criadas na turma.
     * @param idTurma id da turma.
     * @param matriculaAluno matrícula do aluno.
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
     * @param matriculaProfessor matrícula do professor.
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

    /**
     * Retorna todas as turmas disponíveis para adicionar o aluno.
     */
    public List<TurmaDTO> getTurmasDisponiveisByAluno(int matriculaAluno) {
        Optional<AlunoEntity> aluno = alunoRepository.getAlunoByMatricula(matriculaAluno);
        if (aluno.isPresent()) {
            return turmaRepository.getTurmasDisponiveisByAluno(aluno.get()).stream()
                    .map(TurmaDTO::new)
                    .toList();
        }
        return List.of();
    }

    /**
     * Retorna todas as turmas da instituição.
     */
    public List<TurmaDTO> getTurmasByInstituicao(int idInstituicao) {
        return turmaRepository.getTurmasByInstituicao(idInstituicao).stream()
                .map(TurmaDTO::new)
                .toList();
    }

    /**
     * Retorna as turmas ministradas pelo professor.
     */
    public List<TurmaDTO> getTurmasByProfessor(int matriculaProfessor) {
        return turmaRepository.getTurmasByProfessor(matriculaProfessor).stream()
                .map(TurmaDTO::new)
                .toList();
    }
}
