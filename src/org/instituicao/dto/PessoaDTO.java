package org.instituicao.dto;

import java.time.LocalDate;

public class PessoaDTO {
    private final String cpf;
    private final String nome;
    private final LocalDate dataNascimento;
    private final String email;

    public PessoaDTO(String cpf, String nome, LocalDate dataNascimento, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return  "Cpf: " + getCpf()
            + "\nNome: " + getNome()
            + "\nData Nascimento: " + getDataNascimento()
            + "\nEmail: " + getEmail();
    }
}
