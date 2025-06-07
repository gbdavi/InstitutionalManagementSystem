package org.instituicao.service;

import org.instituicao.dto.AlunoDTO;
import org.instituicao.dto.AlunoCadastroDTO;
import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.InstituicaoEntity;
import org.instituicao.repository.AlunoRepository;
import org.instituicao.repository.InstituicaoRepository;

import java.util.Optional;

public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final InstituicaoService instituicaoService;

    public AlunoService() {
        this.alunoRepository = new AlunoRepository();
        this.instituicaoRepository = new InstituicaoRepository();
        this.instituicaoService = new InstituicaoService();
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
        InstituicaoEntity instituicao = instituicaoRepository.getInstituicaoById(alunoCadastroDTO.getIdInstituicao());
        if (instituicao == null) {
            return null;
        }

        AlunoEntity aluno = new AlunoEntity(alunoCadastroDTO.getCpf(), alunoCadastroDTO.getNome(), alunoCadastroDTO.getDataNascimento(), alunoCadastroDTO.getEmail(), instituicao);
        aluno.setSenha(alunoCadastroDTO.getSenha());
        instituicaoService.atribuirDadosAcademicos(instituicao, aluno);

        if (alunoRepository.adicionarAluno(aluno)) {
            return new AlunoDTO(aluno);
        }
        return null;
    }

}
