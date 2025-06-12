package org.instituicao.service;

import org.instituicao.dto.DisciplinaCadastroDTO;
import org.instituicao.dto.DisciplinaDTO;
import org.instituicao.entity.DisciplinaEntity;
import org.instituicao.entity.InstituicaoEntity;
import org.instituicao.repository.DisciplinaRepository;
import org.instituicao.repository.InstituicaoRepository;

import java.util.Optional;

public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;
    private final InstituicaoRepository instituicaoRepository;

    public DisciplinaService() {
        this.disciplinaRepository = new DisciplinaRepository();
        this.instituicaoRepository = new InstituicaoRepository();
    }

    /**
     * Cadastra nova disciplina no banco de dados.
     * @param disciplinaCadastroDTO dados da disciplina.
     * @return dados da disciplina se cadastrada com sucesso, caso contrário null.
     */
    public DisciplinaDTO cadastrar(DisciplinaCadastroDTO disciplinaCadastroDTO) {
        Optional<InstituicaoEntity> instituicao = instituicaoRepository.getInstituicaoById(disciplinaCadastroDTO.getIdInstituicao());
        if (instituicao.isPresent()) {
            DisciplinaEntity disciplina = new DisciplinaEntity(disciplinaCadastroDTO.getNome(), disciplinaCadastroDTO.getEmenta(), instituicao.get());
            if (disciplinaRepository.adicionarDisciplina(disciplina)) {
                return new DisciplinaDTO(disciplina);
            }
        }
        return null;
    }
}
