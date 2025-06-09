package org.instituicao.dto;

import org.instituicao.entity.EntregaEntity;
import org.instituicao.type.StatusEntrega;

public class EntregaDTO {
    private final int id;
    private final AlunoDTO aluno;
    private final AvaliacaoDTO avaliacao;
    private final String resposta;
    private final StatusEntrega status;
    private final float nota;

    public EntregaDTO(int id, AlunoDTO aluno, AvaliacaoDTO avaliacao, String resposta, StatusEntrega status, float nota) {
        this.id = id;
        this.aluno = aluno;
        this.avaliacao = avaliacao;
        this.resposta = resposta;
        this.status = status;
        this.nota = nota;
    }

    public EntregaDTO(EntregaEntity entregaEntity) {
        this.id = entregaEntity.getId();
        this.aluno = new AlunoDTO(entregaEntity.getAluno());
        this.avaliacao = new AvaliacaoDTO(entregaEntity.getAvaliacao());
        this.resposta = entregaEntity.getResposta();
        this.status = entregaEntity.getStatus();
        this.nota = entregaEntity.getNota();
    }

    public int getId() {
        return id;
    }

    public AlunoDTO getAluno() {
        return aluno;
    }

    public AvaliacaoDTO getAvaliacao() {
        return avaliacao;
    }

    public String getResposta() {
        return resposta;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public float getNota() {
        return nota;
    }
}
