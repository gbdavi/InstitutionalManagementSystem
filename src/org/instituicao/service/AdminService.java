package org.instituicao.service;

import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.entity.AdminEntity;
import org.instituicao.entity.FuncionarioEntity;
import org.instituicao.repository.FuncionarioRepository;

import java.util.Optional;

public class AdminService {
    private final FuncionarioRepository funcionarioRepository;

    public AdminService() {
        this.funcionarioRepository = new FuncionarioRepository();
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
}
