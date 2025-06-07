package org.instituicao.dto;

import java.time.LocalDate;

public class AlunoCadastroDTO extends PessoaDTO {
    private final String senha;
    private final int idInstituicao;

    public AlunoCadastroDTO(String cpf, String nome, LocalDate dataNascimento, String email, int idInstituicao, String senha) {
        super(cpf, nome, dataNascimento, email);
        this.idInstituicao = idInstituicao;
        this.senha = senha;
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return  "Cpf: " + getCpf()
            + "\nNome: " + getNome()
            + "\nData Nascimento: " + getDataNascimento()
            + "\nEmail: " + getEmail()
            + "\nId Instituicao: " + getIdInstituicao()
            + "\nSenha: " + getSenha();
    }
}
