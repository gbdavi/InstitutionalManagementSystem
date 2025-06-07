package org.instituicao.dto;

import java.time.LocalDate;

public class FuncionarioCadastroDTO extends PessoaDTO {
    private final int idInstituicao;
    private final String senha;

    public FuncionarioCadastroDTO(String cpf, String nome, LocalDate dataNascimento, String email, int idInstituicao, String senha) {
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
