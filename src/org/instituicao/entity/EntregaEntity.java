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

    /**
     * Verifica se a nota � v�lida, atribui a nota e altera o status para AVALIADO.
     */
    public boolean setNota(float nota) {
        if (status != StatusEntrega.PENDENTE && nota >= 0 && nota <= 10) {
            this.nota = nota;
            this.status = StatusEntrega.AVALIADO;
            return true;
        }
        return false;
    }

    /**
     * Gera o hashcode da inst�ncia.
     * Obs.: necess�rio para utiliza��o do HashSet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara a inst�ncia atual com outra fornecida.
     * Obs.: necess�rio para utiliza��o do HashSet.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntregaEntity other = (EntregaEntity) obj;
		return Objects.equals(id, other.getId());
    }
}
