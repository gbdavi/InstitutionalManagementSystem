package org.instituicao.service;

import org.instituicao.dto.InstituicaoCadastroDTO;
import org.instituicao.dto.InstituicaoDTO;
import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.FuncionarioEntity;
import org.instituicao.entity.InstituicaoEntity;
import org.instituicao.repository.InstituicaoRepository;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.List;
import java.util.Optional;

public class InstituicaoService {
    private static final IdSequencialGenerator matriculaGenerator = new IdSequencialGenerator(100);

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoService() {
        this.instituicaoRepository = new InstituicaoRepository();
    }

    /**
     * Cadastra nova instituição no banco de dados.
     * @param instituicaoCadastroDTO dados de cadastro da instituição.
     * @return dados da instituição se cadastrada com sucesso, caso contrário null.
     */
    public InstituicaoDTO cadastrar(InstituicaoCadastroDTO instituicaoCadastroDTO) {
        InstituicaoEntity instituicao = new InstituicaoEntity(instituicaoCadastroDTO.getNome(), instituicaoCadastroDTO.getAliasCorporativo(), instituicaoCadastroDTO.getAliasAcademico());
        if (instituicaoRepository.adicionarInstituicao(instituicao)) {
            return new InstituicaoDTO(instituicao);
        }
        return null;
    }

    /**
     * Atribui ao aluno dados de matrícula e email acadêmico.
     */
    public boolean atribuirDadosAcademicos(InstituicaoEntity instituicao, AlunoEntity aluno) {
        if (instituicao != null && aluno != null) {
            int matricula = matriculaGenerator.gerarProximoId();
            aluno.setMatricula(matricula);
            aluno.setEmailAcademico(matricula + "@" + instituicao.getAliasAcademico());
            return true;
        }
        return false;
    }

    /**
     * Atribui ao funcionário dados de matrícula e email corporativo.
     */
    public boolean atribuirDadosCorporativos(InstituicaoEntity instituicao, FuncionarioEntity funcionario) {
        if (instituicao != null && funcionario != null) {
            int matricula = matriculaGenerator.gerarProximoId();
            funcionario.setMatricula(matricula);
            funcionario.setEmailCorporativo(matricula + "@" + instituicao.getAliasCorporativo());
            return true;
        }
        return false;
    }

    /**
     * @return lista de instituições cadastradas no banco de dados.
     */
    public List<InstituicaoDTO> getInstituicoes() {
        return instituicaoRepository.getInstituicoes().stream()
                .map(InstituicaoDTO::new)
                .toList();
    }

}
