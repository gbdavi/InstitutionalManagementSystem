package org.instituicao.repository;

import org.instituicao.entity.FuncionarioEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class FuncionarioRepository {
    public static HashSet<FuncionarioEntity> funcionarioEntities = new HashSet<>();

    /**
     * Adiciona funcion�rio ao reposit�rio local.
     */
    public boolean adicionarFuncionario(FuncionarioEntity funcionario) {
        return funcionario != null && funcionarioEntities.add(funcionario);
    }

    /**
     * Remove funcion�rio do reposit�rio local.
     */
    public boolean removerFuncionario(FuncionarioEntity funcionario) {
        return funcionarioEntities.remove(funcionario);
    }

    /**
     * Busca funcion�rio no reposit�rio local atrav�s da matr�cula do funcion�rio.
     */
    public Optional<FuncionarioEntity> getFuncionarioByMatricula(int matriculaFuncionario) {
        return funcionarioEntities.stream()
                .filter(funcionario -> funcionario.getMatricula() == matriculaFuncionario)
                .findFirst();
    }

    /**
     * Busca funcion�rio no reposit�rio local atrav�s do email corporativo.
     */
    public Optional<FuncionarioEntity> getFuncionarioByEmailCorporativo(String email) {
        return funcionarioEntities.stream()
                .filter(funcionario -> funcionario.getEmailCorporativo().equalsIgnoreCase(email))
                .findFirst();
    }

    /**
     * Busca disciplinas no reposit�rio local atrav�s do id da institui��o.
     */
    public List<FuncionarioEntity> getFuncionarioByInstituicao(int idInstituicao) {
        return funcionarioEntities.stream()
                .filter(funcionario -> funcionario.getInstituicaoEntity().getId() == idInstituicao)
                .toList();
    }

}
