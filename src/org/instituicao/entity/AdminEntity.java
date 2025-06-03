package org.instituicao.entity;

import java.time.LocalDate;

public class AdminEntity extends FuncionarioEntity {
    public AdminEntity(String cpf, String nome, LocalDate dataNascimento, String email, InstituicaoEntity instituicaoEntity) {
        super(cpf, nome, dataNascimento, email, instituicaoEntity);
    }

}
