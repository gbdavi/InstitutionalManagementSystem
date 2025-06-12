package org.instituicao.repository;

import org.instituicao.entity.FuncionarioEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class FuncionarioRepository {
    public static HashSet<FuncionarioEntity> funcionarioEntities = new HashSet<>();

    /**
     * Adiciona funcionário ao repositório local.
     */
    public boolean adicionarFuncionario(FuncionarioEntity funcionario) {
        return funcionario != null && funcionarioEntities.add(funcionario);
    }

    /**
     * Remove funcionário do repositório local.
     */
    public boolean removerFuncionario(FuncionarioEntity funcionario) {
        return funcionarioEntities.remove(funcionario);
    }

    /**
     * Busca funcionário no repositório local através da matrícula do funcionário.
     */
    public Optional<FuncionarioEntity> getFuncionarioByMatricula(int matriculaFuncionario) {
        return funcionarioEntities.stream()
                .filter(funcionario -> funcionario.getMatricula() == matriculaFuncionario)
                .findFirst();
    }

    /**
     * Busca funcionário no repositório local através do email corporativo.
     */
    public Optional<FuncionarioEntity> getFuncionarioByEmailCorporativo(String email) {
        return funcionarioEntities.stream()
                .filter(funcionario -> funcionario.getEmailCorporativo().equalsIgnoreCase(email))
                .findFirst();
    }

    /**
     * Busca disciplinas no repositório local através do id da instituição.
     */
    public List<FuncionarioEntity> getFuncionarioByInstituicao(int idInstituicao) {
        return funcionarioEntities.stream()
                .filter(funcionario -> funcionario.getInstituicaoEntity().getId() == idInstituicao)
                .toList();
    }

}
