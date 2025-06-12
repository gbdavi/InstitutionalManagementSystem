package org.instituicao.service;

import org.instituicao.dto.AvaliacaoDTO;
import org.instituicao.dto.AvaliacaoCadastroDTO;
import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.AvaliacaoEntity;
import org.instituicao.entity.EntregaEntity;
import org.instituicao.entity.TurmaEntity;
import org.instituicao.repository.AvaliacaoRepository;
import org.instituicao.repository.EntregaRepository;
import org.instituicao.repository.TurmaRepository;
import org.instituicao.type.StatusEntrega;

import java.util.List;
import java.util.Optional;

public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final TurmaRepository turmaRepository;
    private final EntregaRepository entregaRepository;

    public AvaliacaoService() {
        this.avaliacaoRepository = new AvaliacaoRepository();
        this.turmaRepository = new TurmaRepository();
        this.entregaRepository = new EntregaRepository();
    }

    /**
     * Cadastra nova avaliação no banco de dados.
     * @param avaliacaoCadastroDTO dados de cadastro da avaliação.
     * @return dados da avaliação se cadastrada com sucesso, caso contrário null.
     */
    public AvaliacaoDTO cadastrar(AvaliacaoCadastroDTO avaliacaoCadastroDTO) {
        Optional<TurmaEntity> turma = turmaRepository.getTurmaById(avaliacaoCadastroDTO.getIdTurma());
        if (turma.isPresent()) {
            AvaliacaoEntity avaliacao = new AvaliacaoEntity(avaliacaoCadastroDTO.getDescricao(), turma.get());
            if (avaliacaoRepository.adicionarAvaliacao(avaliacao)) {
                for (AlunoEntity aluno : turma.get().getAlunos()) {
                    entregaRepository.adicionarEntrega(new EntregaEntity(aluno, avaliacao));
                }
                return new AvaliacaoDTO(avaliacao);
            }
        }
        return null;
    }

    /**
     * Envia a resposta do aluno para a avaliação e define como entregue.
     * @param idAvaliacao id da avaliação.
     * @param matriculaAluno matrícula do aluno.
     * @param resposta resposta do aluno.
     */
    public boolean entregarAvaliacao(int idAvaliacao, int matriculaAluno, String resposta) {
        Optional<EntregaEntity> entrega = entregaRepository.getEntregaByAvaliacaoAluno(idAvaliacao, matriculaAluno);
        if (entrega.isPresent() && entrega.get().getStatus() == StatusEntrega.PENDENTE) {
            entrega.get().setResposta(resposta);
            entrega.get().setStatus(StatusEntrega.ENTREGUE);
            return true;
        }
        return false;
    }


    /**
     * Atribui nota para a avaliação entregue pelo aluno.
     * @param idAvaliacao id da avaliação.
     * @param matriculaAluno id do aluno.
     * @param nota nota.
     */
    public boolean avaliarEntrega(int idAvaliacao, int matriculaAluno, float nota) {
        Optional<EntregaEntity> entrega = entregaRepository.getEntregaByAvaliacaoAluno(idAvaliacao, matriculaAluno);
        if (entrega.isPresent() && entrega.get().getStatus() != StatusEntrega.PENDENTE) {
            entrega.get().setNota(nota);
            return true;
        }
        return false;
    }

    /**
     * Retorna as avaliações pendentes de um aluno no banco de dados.
     */
    public List<AvaliacaoDTO> getAvaliacoesPendentesByAluno(int matriculaAluno) {
        return entregaRepository.getEntregasPendentesByAluno(matriculaAluno).stream()
            .map(entregaEntity -> new AvaliacaoDTO(entregaEntity.getAvaliacao()))
            .toList();
    }
}
