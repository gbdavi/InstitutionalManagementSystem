package org.instituicao.entity;

import org.instituicao.util.security.SenhaUtils;

import java.time.LocalDate;
import java.util.Objects;

public class FuncionarioEntity extends PessoaEntity {
    private int matricula;
    private String emailCorporativo;
    private String senhaHash;
    private final InstituicaoEntity instituicaoEntity;

    public FuncionarioEntity(String cpf, String nome, LocalDate dataNascimento, String email, InstituicaoEntity instituicaoEntity) {
        super(cpf, nome, dataNascimento, email);
        if (instituicaoEntity == null) {
            throw new NullPointerException("O parâmetro 'instituicaoEntity' não pode ser nulo");
        }
        this.instituicaoEntity = instituicaoEntity;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    /**
     * Gera hash da senha e compara com hash na variável senhaHash".
     */
    public boolean verificarSenha(String senha) {
        return senha != null && senhaHash != null && SenhaUtils.verificarSenha(senha, senhaHash);
    }

    /**
     * Gera hash da senha e salva na variável senhaHash.
     */
    public void setSenha(String senha) {
        this.senhaHash = SenhaUtils.gerarHashSenha(senha);
    }

    public InstituicaoEntity getInstituicaoEntity() {
        return instituicaoEntity;
    }

    @Override
    public String toString() {
        return getMatricula() + " - " + getNome() + " - " + getEmailCorporativo();
    }

    /**
     * Gera o hashcode da instância.
     * Obs.: necessário para utilização do HashSet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    /**
     * Compara a instância atual com outra fornecida.
     * Obs.: necessário para utilização do HashSet.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioEntity other = (FuncionarioEntity) obj;
		return Objects.equals(matricula, other.getMatricula());
    }
}
