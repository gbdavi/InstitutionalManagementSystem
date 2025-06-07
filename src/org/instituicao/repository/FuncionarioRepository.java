package org.instituicao.repository;

import org.instituicao.entity.FuncionarioEntity;

import java.util.HashSet;
import java.util.Optional;

public class FuncionarioRepository {
    public static HashSet<FuncionarioEntity> funcionarioEntities = new HashSet<>();

    public boolean adicionarFuncionario(FuncionarioEntity funcionario) {
        return funcionario != null && funcionarioEntities.add(funcionario);
    }

    public boolean removerFuncionario(FuncionarioEntity funcionario) {
        return funcionarioEntities.remove(funcionario);
    }

    public Optional<FuncionarioEntity> getFuncionarioByEmailCorporativo(String email) {
        return funcionarioEntities.stream().filter(funcionario -> funcionario.getEmailCorporativo().equalsIgnoreCase(email)).findFirst();
    }

}
