package org.instituicao.service;

import org.instituicao.dto.FuncionarioCadastroDTO;
import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.entity.AdminEntity;
import org.instituicao.entity.FuncionarioEntity;
import org.instituicao.entity.InstituicaoEntity;
import org.instituicao.repository.FuncionarioRepository;
import org.instituicao.repository.InstituicaoRepository;

import java.util.Optional;

public class AdminService {
    private final FuncionarioRepository funcionarioRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final InstituicaoService instituicaoService;

    public AdminService() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.instituicaoRepository = new InstituicaoRepository();
        this.instituicaoService = new InstituicaoService();
    }

    /**
     * Autentica o admin cadastrado
     * @param emailCorporativo email corporativo do admin
     * @param senha senha do admin
     * @return instancia do admin se autenticado com sucesso, caso contrario null
     */
    public FuncionarioDTO login(String emailCorporativo, String senha) {
        Optional<FuncionarioEntity> funcionario = funcionarioRepository.getFuncionarioByEmailCorporativo(emailCorporativo);
        if (funcionario.isPresent() && funcionario.get().verificarSenha(senha) && funcionario.get() instanceof AdminEntity) {
            return new FuncionarioDTO(funcionario.get());
        }
        return null;
    }

    /**
     * Cadastra novo admin na base de dados.
     * @param adminCadastroDTO dados do admin.
     * @return dados do admin se cadastrado com sucesso, caso contrário null.
     */
    public FuncionarioDTO cadastrar(FuncionarioCadastroDTO adminCadastroDTO) {
        Optional<InstituicaoEntity> instituicao = instituicaoRepository.getInstituicaoById(adminCadastroDTO.getIdInstituicao());
        if (!instituicao.isPresent()) {
            return null;
        }

        AdminEntity admin = new AdminEntity(adminCadastroDTO.getCpf(), adminCadastroDTO.getNome(), adminCadastroDTO.getDataNascimento(), adminCadastroDTO.getEmail(), instituicao.get());
        admin.setSenha(adminCadastroDTO.getSenha());
        instituicaoService.atribuirDadosCorporativos(instituicao.get(), admin);

        if (funcionarioRepository.adicionarFuncionario(admin)) {
            return new FuncionarioDTO(admin);
        }
        return null;
    }
}
