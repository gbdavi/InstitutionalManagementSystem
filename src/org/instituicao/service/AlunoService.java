package org.instituicao.service;

import org.instituicao.dto.AlunoDTO;
import org.instituicao.entity.AlunoEntity;
import org.instituicao.repository.AlunoRepository;

import java.util.Optional;

public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService() {
        this.alunoRepository = new AlunoRepository();
    }

    /**
     * Autentica o aluno cadastrado
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

}
