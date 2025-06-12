package org.instituicao.repository;

import org.instituicao.dto.CursoCadastroDTO;
import org.instituicao.dto.CursoDTO;
import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.CursoEntity;
import org.instituicao.entity.DisciplinaEntity;
import org.instituicao.entity.InstituicaoEntity;

import java.util.List;
import java.util.Optional;

public class CursoService {
    private final CursoRepository cursoRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public CursoService() {
        this.cursoRepository = new CursoRepository();
        this.instituicaoRepository = new InstituicaoRepository();
        this.alunoRepository = new AlunoRepository();
        this.disciplinaRepository = new DisciplinaRepository();
    }

    /**
     * Cadastra novo curso no banco de dados.
     * @param cursoCadastroDTO dados do curso.
     * @return dados do curso se cadastrado com sucesso, caso contrário null.
     */
    public CursoDTO cadastrar(CursoCadastroDTO cursoCadastroDTO) {
        Optional<InstituicaoEntity> instituicao = instituicaoRepository.getInstituicaoById(cursoCadastroDTO.getIdInstituicao());
        if (instituicao.isPresent()) {
            CursoEntity curso = new CursoEntity(cursoCadastroDTO.getNome(), instituicao.get());
            if (cursoRepository.adicionarCurso(curso)) {
                return new CursoDTO(curso);
            }
        }
        return null;
    }

    /**
     * Adiciona aluno no curso.
     * @param idCurso id do curso.
     * @param matriculaAluno matrícula do aluno.
     */
    public boolean adicionarAluno(int idCurso, int matriculaAluno) {
        Optional<CursoEntity> curso = cursoRepository.getCursoById(idCurso);
        Optional<AlunoEntity> aluno = alunoRepository.getAlunoByMatricula(matriculaAluno);

        if (curso.isPresent() && aluno.isPresent()) {
            return aluno.get().adicionarCurso(curso.get());
        }
        return false;
    }
    /**
     * Adiciona disciplina ao curso.
     * @param idCurso id do curso.
     * @param idDisciplina id da disciplina.
     */
    public boolean adicionarDisciplina(int idCurso, int idDisciplina) {
        Optional<CursoEntity> curso = cursoRepository.getCursoById(idCurso);
        Optional<DisciplinaEntity> disciplina = disciplinaRepository.getDisciplinaById(idDisciplina);

        if (curso.isPresent() && disciplina.isPresent()) {
            return curso.get().adicionarDisciplina(disciplina.get());
        }
        return false;
    }

    /**
     * @return lista de cursos cadastrados na instituição no banco de dados.
     */
    public List<CursoDTO> getCursos(int idInstituicao) {
        return cursoRepository.getCursosByInstituicao(idInstituicao).stream()
                .map(CursoDTO::new)
                .toList();
    }
}
