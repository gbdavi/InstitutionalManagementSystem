package org.instituicao.service;

import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.entity.FuncionarioEntity;
import org.instituicao.entity.ProfessorEntity;
import org.instituicao.repository.FuncionarioRepository;

import java.util.Optional;

public class ProfessorService {
    private final FuncionarioRepository funcionarioRepository;

    public ProfessorService() {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    /**
     * Autentica o professor cadastrado
     * @param emailCorporativo email corporativo do professor
     * @param senha senha do professor
     * @return instancia do professor se autenticado com sucesso, caso contrario null
     */
    public FuncionarioDTO login(String emailCorporativo, String senha) {
        Optional<FuncionarioEntity> funcionario = funcionarioRepository.getFuncionarioByEmailCorporativo(emailCorporativo);
        if (funcionario.isPresent() && funcionario.get().verificarSenha(senha) && funcionario.get() instanceof ProfessorEntity) {
            return new FuncionarioDTO(funcionario.get());
        }
        return null;
    }
}
