package org.instituicao.dto;

import org.instituicao.entity.AlunoEntity;

import java.time.LocalDate;

public class AlunoDTO extends PessoaDTO {
    private final int matricula;
    private final String emailAcademico;
    private final int idInstituicao;

    public AlunoDTO(String cpf, String nome, LocalDate dataNascimento, String email, int idInstituicao, int matricula, String emailAcademico) {
        super(cpf, nome, dataNascimento, email);
        this.idInstituicao = idInstituicao;
        this.matricula = matricula;
        this.emailAcademico = emailAcademico;
    }

    public AlunoDTO(AlunoEntity aluno) {
        super(aluno.getCpf(), aluno.getNome(), aluno.getDataNascimento(), aluno.getEmail());
        this.matricula = aluno.getMatricula();
        this.emailAcademico = aluno.getEmailAcademico();
        this.idInstituicao = aluno.getInstituicaoEntity().getId();
    }

    public int getMatricula() {
        return matricula;
    }

    public String getEmailAcademico() {
        return emailAcademico;
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }

    @Override
    public String toString() {
        return matricula + " - " + getNome() + " | " + getEmailAcademico();
    }
}
