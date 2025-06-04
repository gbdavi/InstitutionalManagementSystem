package org.instituicao.dto;

import java.time.LocalDate;

public class FuncionarioDTO extends PessoaDTO {
    private final int matricula;
    private final String emailCorporativo;
    private final int idInstituicao;

    public FuncionarioDTO(String cpf, String nome, LocalDate dataNascimento, String email, int matricula, String emailCorporativo, int idInstituicao) {
        super(cpf, nome, dataNascimento, email);
        this.matricula = matricula;
        this.emailCorporativo = emailCorporativo;
        this.idInstituicao = idInstituicao;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }
}
