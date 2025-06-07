package org.instituicao.dto;

import org.instituicao.entity.AlunoEntity;

import java.time.LocalDate;

public class AlunoDTO extends PessoaDTO {
    private final int matricula;
    private final String emailAcademico;
    private final int idInstituicao;

    public AlunoDTO(String cpf, String nome, LocalDate dataNascimento, String email, int matricula, String emailAcademico, int idInstituicao) {
        super(cpf, nome, dataNascimento, email);
        this.matricula = matricula;
        this.emailAcademico = emailAcademico;
        this.idInstituicao = idInstituicao;
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
}
