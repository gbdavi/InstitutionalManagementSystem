package org.instituicao.entity;


import org.instituicao.type.StatusEntrega;
import org.instituicao.util.id.IdGenerator;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.Objects;

public class EntregaEntity {
    private static IdGenerator idGenerator = new IdSequencialGenerator(1);
    private int id;
    private AlunoEntity alunoEntity;
    private AvaliacaoEntity avaliacaoEntity;
    private String resposta;
    private StatusEntrega status = StatusEntrega.PENDENTE;
    private float nota;

    public EntregaEntity(AlunoEntity alunoEntity, AvaliacaoEntity avaliacaoEntity) {
        this.id = idGenerator.gerarProximoId();
        this.alunoEntity = alunoEntity;
        this.avaliacaoEntity = avaliacaoEntity;
    }

    public int getId() {
        return id;
    }

    public AlunoEntity getAluno() {
        return alunoEntity;
    }

    public AvaliacaoEntity getAvaliacao() {
        return avaliacaoEntity;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public String getResposta() {
        return this.resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public float getNota() {
        return nota;
    }

    public boolean setNota(float nota) {
        if (status == StatusEntrega.ENTREGUE && nota >= 0 && nota <= 10) {
            this.nota = nota;
            this.status = StatusEntrega.AVALIADO;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
