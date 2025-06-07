package org.instituicao.service;

import org.instituicao.dto.InstituicaoDTO;
import org.instituicao.entity.AlunoEntity;
import org.instituicao.entity.FuncionarioEntity;
import org.instituicao.entity.InstituicaoEntity;
import org.instituicao.repository.InstituicaoRepository;
import org.instituicao.util.id.IdSequencialGenerator;

import java.util.List;

public class InstituicaoService {
    private static final IdSequencialGenerator matriculaGenerator = new IdSequencialGenerator(100);

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoService() {
        this.instituicaoRepository = new InstituicaoRepository();
    }

    public boolean atribuirDadosAcademicos(InstituicaoEntity instituicao, AlunoEntity aluno) {
        if (instituicao != null && aluno != null) {
            int matricula = matriculaGenerator.gerarProximoId();
            aluno.setMatricula(matricula);
            aluno.setEmailAcademico(matricula + "@" + instituicao.getAliasAcademico());
            return true;
        }
        return false;
    }

    public boolean atribuirDadosCorporativos(InstituicaoEntity instituicao, FuncionarioEntity funcionario) {
        if (instituicao != null && funcionario != null) {
            int matricula = matriculaGenerator.gerarProximoId();
            funcionario.setMatricula(matricula);
            funcionario.setEmailCorporativo(matricula + "@" + instituicao.getAliasCorporativo());
            return true;
        }
        return false;
    }

    public List<InstituicaoDTO> getInstituicoes() {
        return instituicaoRepository.getInstituicoes().stream()
                .map(InstituicaoDTO::new)
                .toList();
    }

}
