package org.instituicao.service;

import org.instituicao.dto.*;
import org.instituicao.entity.*;
import org.instituicao.repository.*;
import org.instituicao.type.SituacaoDisciplina;
import org.instituicao.type.StatusTurma;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final InstituicaoService instituicaoService;
    private final EntregaRepository entregaRepository;
    private final TurmaRepository turmaRepository;

    public AlunoService() {
        this.alunoRepository = new AlunoRepository();
        this.instituicaoRepository = new InstituicaoRepository();
        this.instituicaoService = new InstituicaoService();
        this.entregaRepository = new EntregaRepository();
        this.turmaRepository = new TurmaRepository();
    }

    /**
     * Autentica o aluno cadastrado.
     * @param emailAcademico email academico do aluno
     * @param senha senha do aluno
     * @return instancia do aluno se autenticado com sucesso, caso contrario null
     */
    public AlunoDTO login(String emailAcademico, String senha) {
        Optional<AlunoEntity> aluno = alunoRepository.getAlunoByEmailAcademico(emailAcademico);
        if (aluno.isPresent() && aluno.get().verificarSenha(senha)) {
            return new AlunoDTO(aluno.get());
        }
        return null;
    }

    /**
     * Cadastra novo aluno na base de dados.
     * @param alunoCadastroDTO dados do aluno.
     * @return dados do aluno se cadastrado com sucesso, caso contrário null.
     */
    public AlunoDTO cadastrar(AlunoCadastroDTO alunoCadastroDTO) {
        Optional<InstituicaoEntity> instituicao = instituicaoRepository.getInstituicaoById(alunoCadastroDTO.getIdInstituicao());
        if (!instituicao.isPresent()) {
            return null;
        }

        AlunoEntity aluno = new AlunoEntity(alunoCadastroDTO.getCpf(), alunoCadastroDTO.getNome(), alunoCadastroDTO.getDataNascimento(), alunoCadastroDTO.getEmail(), instituicao.get());
        aluno.setSenha(alunoCadastroDTO.getSenha());
        instituicaoService.atribuirDadosAcademicos(instituicao.get(), aluno);

        if (alunoRepository.adicionarAluno(aluno)) {
            return new AlunoDTO(aluno);
        }
        return null;
    }

    /**
     * Relatório completo de desempenho do aluno em disciplinas já iniciadas ou concluídas, agrupadas por curso e ordenadas pelo nome da disciplina.
     * @param matriculaAluno matrícula do aluno.
     * @return dados do relatório se aluno existir, caso contrário null.
     */
    public RelatorioAlunoDTO getRelatorio(int matriculaAluno) {
        Optional<AlunoEntity> aluno = alunoRepository.getAlunoByMatricula(matriculaAluno);
        if (!aluno.isPresent()) {
            return null;
        }

        List<TurmaEntity> turmasAluno = turmaRepository.getTurmasByAluno(matriculaAluno);

        List<RelatorioCursoDTO> relatorioCursos = aluno.get().getCursos().stream().map(curso -> new RelatorioCursoDTO(
            new CursoDTO(curso),
            curso.getDisciplinas().stream()
                .map(disciplina -> {
                    DisciplinaDTO disciplinaDTO = new DisciplinaDTO(disciplina);
                    Optional<TurmaEntity> turmaDisciplina = turmasAluno.stream().filter(turmaEntity -> turmaEntity.getDisciplina() == disciplina).findFirst();
                    if (turmaDisciplina.isPresent()) {
                        if (turmaDisciplina.get().getStatus() == StatusTurma.CONCLUIDA) {
                            float media = calcularMediaDisciplina(matriculaAluno, turmaDisciplina.get());
                            SituacaoDisciplina situacaoDisciplina = media >= 7 ? SituacaoDisciplina.APROVADO : SituacaoDisciplina.REPROVADO;
                            return new RelatorioDisciplinaDTO(disciplinaDTO, media, situacaoDisciplina);
                        }
                        SituacaoDisciplina situacaoDisciplina = turmaDisciplina.get().getStatus() == StatusTurma.NAO_INICIADA ? SituacaoDisciplina.A_CURSAR : SituacaoDisciplina.CURSANDO;
                        return new RelatorioDisciplinaDTO(disciplinaDTO, null, situacaoDisciplina);
                    }
                    return new RelatorioDisciplinaDTO(disciplinaDTO, null, SituacaoDisciplina.A_CURSAR);
                }).sorted(Comparator.comparing(o -> o.getDisciplina().getNome()))
            .toList()
        )).toList();

        return new RelatorioAlunoDTO(matriculaAluno, aluno.get().getNome(), relatorioCursos);
    }

    /**
     * Calcula média aritmética das notas do aluno na disciplina.
     * @param matriculaAluno matrícula do aluno.
     * @param turmaAluno turma que se deseja obter a nota média do aluno.
     */
    private float calcularMediaDisciplina(int matriculaAluno, TurmaEntity turmaAluno) {
        List<EntregaEntity> entregas = entregaRepository.getEntregasByTurmaAluno(turmaAluno.getId(), matriculaAluno);
        if (entregas.size() > 0) {
            float soma = entregas.stream()
                    .map(EntregaEntity::getNota)
                    .reduce(0f, Float::sum);
            return soma / entregas.size();
        }
        return 0;
    }

    /**
     * @return lista de alunos cadastrados na instituição no banco de dados.
     */
    public List<AlunoDTO> getAlunos(int idInstituicao) {
        return alunoRepository.getAlunosByInstituicao(idInstituicao).stream()
                .map(AlunoDTO::new)
                .toList();
    }
}
