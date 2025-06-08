package org.instituicao.service;

import org.instituicao.dto.AvaliacaoDTO;
import org.instituicao.dto.AvaliacaoCadastroDTO;
import org.instituicao.entity.AvaliacaoEntity;
import org.instituicao.entity.TurmaEntity;
import org.instituicao.repository.AvaliacaoRepository;
import org.instituicao.repository.TurmaRepository;

import java.util.Optional;

public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final TurmaRepository turmaRepository;

    public AvaliacaoService() {
        this.avaliacaoRepository = new AvaliacaoRepository();
        this.turmaRepository = new TurmaRepository();
    }

    /**
     * Cadastra nova avaliação na base de dados.
     * Equivalente a rota /avaliacoes/cadastrar.
     * @param avaliacaoCadastroDTO dados de cadastro da avaliação.
     * @return dados da avaliação se cadastrada com sucesso, caso contrário null.
     */
    public AvaliacaoDTO cadastrar(AvaliacaoCadastroDTO avaliacaoCadastroDTO) {
        Optional<TurmaEntity> turma = turmaRepository.getTurmaById(avaliacaoCadastroDTO.getIdTurma());
        if (turma.isPresent()) {
            AvaliacaoEntity avaliacao = new AvaliacaoEntity(avaliacaoCadastroDTO.getDescricao(), turma.get());
            if (avaliacaoRepository.adicionarAvaliacao(avaliacao)) {
                return new AvaliacaoDTO(avaliacao);
            }
        }
        return null;
    }


}
