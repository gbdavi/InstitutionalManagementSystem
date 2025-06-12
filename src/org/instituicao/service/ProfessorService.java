package org.instituicao.service;

import org.instituicao.dto.FuncionarioCadastroDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.entity.FuncionarioEntity;
import org.instituicao.entity.InstituicaoEntity;
import org.instituicao.entity.ProfessorEntity;
import org.instituicao.repository.FuncionarioRepository;
import org.instituicao.repository.InstituicaoRepository;

import java.util.List;
import java.util.Optional;

public class ProfessorService {
    private final FuncionarioRepository funcionarioRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final InstituicaoService instituicaoService;

    public ProfessorService() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.instituicaoRepository = new InstituicaoRepository();
        this.instituicaoService = new InstituicaoService();
    }

    /**
     * Autentica o professor cadastrado.
     * @param emailCorporativo email corporativo do professor.
     * @param senha senha do professor.
     * @return instancia do professor se autenticado com sucesso, caso contrario null.
     */
    public FuncionarioDTO login(String emailCorporativo, String senha) {
        Optional<FuncionarioEntity> funcionario = funcionarioRepository.getFuncionarioByEmailCorporativo(emailCorporativo);
        if (funcionario.isPresent() && funcionario.get() instanceof ProfessorEntity && funcionario.get().verificarSenha(senha)) {
            return new FuncionarioDTO(funcionario.get());
        }
        return null;
    }

    /**
     * Cadastra novo professor no banco de dados.
     * @param professorCadastroDTO dados do professor.
     * @return dados do professor se cadastrado com sucesso, caso contrário null.
     */
    public FuncionarioDTO cadastrar(FuncionarioCadastroDTO professorCadastroDTO) {
        Optional<InstituicaoEntity> instituicao = instituicaoRepository.getInstituicaoById(professorCadastroDTO.getIdInstituicao());
        if (instituicao.isEmpty()) {
            return null;
        }

        ProfessorEntity professor = new ProfessorEntity(
                professorCadastroDTO.getCpf(),
                professorCadastroDTO.getNome(),
                professorCadastroDTO.getDataNascimento(),
                professorCadastroDTO.getEmail(),
                instituicao.get());
        professor.setSenha(professorCadastroDTO.getSenha());
        instituicaoService.atribuirDadosCorporativos(instituicao.get(), professor);

        if (funcionarioRepository.adicionarFuncionario(professor)) {
            return new FuncionarioDTO(professor);
        }
        return null;
    }

    /**
     * Busca todos os professores cadastrados na instituicao.
     */
    public List<FuncionarioDTO> getProfessores(int idInstituicao) {
        return funcionarioRepository.getFuncionarioByInstituicao(idInstituicao).stream()
                .filter(funcionario -> funcionario instanceof ProfessorEntity)
                .map(FuncionarioDTO::new)
                .toList();
    }
}
