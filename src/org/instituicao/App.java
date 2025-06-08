package org.instituicao;

import org.instituicao.controller.*;
import org.instituicao.dto.*;
import org.instituicao.type.StatusTurma;
import org.instituicao.ui.AlunoView;
import org.instituicao.ui.UI;
import org.instituicao.util.data.DataUtils;

public class App {
    public static void main(String[] args) {
        // Inicializar controllers
        InstituicaoController instituicaoController = new InstituicaoController();
        CursoController cursoController = new CursoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        TurmaController turmaController = new TurmaController();
        AvaliacaoController avaliacaoController = new AvaliacaoController();
        AlunoController alunoController = new AlunoController();
        ProfessorController professorController = new ProfessorController();
        AdminController adminController = new AdminController();

        // Inicializar dados de exemplo
        InstituicaoDTO instituicao = instituicaoController.cadastrar(new InstituicaoCadastroDTO("Anima", "ulife.com", "ulife.com"));

        DisciplinaDTO disciplina = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Programa��o de Solu��es Computacionais", "No��es de geometria an�litica e teoria de conjuntos �lgebra e L�gica booleana Algoritmos e suas representa��es L�gica de programa��o entrada, processamento, sa�da Constantes e vari�veis Estruturas de controle: sele��o e repeti��o Fun��es e procedimentos Passagem de par�metros por valor e refer�ncia Opera��es com vetores e matrizes Tipos abstratos de dados: pilhas, filas e listas Algoritmos de pesquisa e de ordena��o Paradigmas de Linguagens de Programa��o Programa��o orientada a objetos: classes, objetos, m�todos, atributos, construtores e m�todos de acesso e modificadores Encapsulamento, heran�a, abstra��o e polimorfismo Tratamento de exce��es Manipula��o de arquivos e conex�o com SGBD (Sistemas Gerenciadores de Banco de Dados) Bibliotecas de interfaces gr�ficas Linguagem SQL de banco de dados.", instituicao.getId()));

        CursoDTO curso = cursoController.cadastrar(new CursoCadastroDTO("Banco de dados: Big Data, Data Science & BI", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina.getId());

        TurmaDTO turma = turmaController.cadastrar(new TurmaCadastroDTO(disciplina.getId()));
        turmaController.alterarStatus(turma.getId(), StatusTurma.EM_ANDAMENTO);

        AvaliacaoDTO avaliacao = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A3 - Cadastro e Gerenciamento de Alunos", turma.getId()));

        // Inicializar usu�rios pr�-cadastrados
        AlunoCadastroDTO alunoCadastroDTO = new AlunoCadastroDTO("00012345678", "Davi Gramm Bauer", DataUtils.processarDataNascimento("27/09/2004"),"davi@mail.com", instituicao.getId(), "aluno123");
        AlunoDTO aluno = alunoController.cadastrar(alunoCadastroDTO);
        cursoController.adicionarAluno(curso.getId(), aluno.getMatricula());
        turmaController.adicionarAluno(turma.getId(), aluno.getMatricula());
        avaliacaoController.entregarAvaliacao(avaliacao.getId(), aluno.getMatricula(), "Resposta exemplo da avalia��o.");

        FuncionarioCadastroDTO professorCadastroDTO = new FuncionarioCadastroDTO("01234567890", "John Doe", DataUtils.processarDataNascimento("01/01/1980"), "john@mail.com", instituicao.getId(), "professor123");
        FuncionarioDTO professor = professorController.cadastrar(professorCadastroDTO);
        turmaController.adicionarProfessor(turma.getId(), professor.getMatricula());
        avaliacaoController.avaliarEntrega(avaliacao.getId(), aluno.getMatricula(), 9.5f);

        FuncionarioCadastroDTO adminCadastroDTO = new FuncionarioCadastroDTO("98765432100", "root", DataUtils.processarDataNascimento("25/12/1975"), "root@mail.com", instituicao.getId(), "admin");
        FuncionarioDTO admin = adminController.cadastrar(adminCadastroDTO);



        turmaController.alterarStatus(turma.getId(), StatusTurma.CONCLUIDA);

        RelatorioAlunoDTO relatorio = alunoController.getRelatorio(100);
        new AlunoView().exibirRelatorio(relatorio);


//        System.out.println("*** Logins pr�-cadastrados ***");
//        System.out.printf("Aluno - email: %s - senha: %s\n", aluno.getEmailAcademico(), alunoCadastroDTO.getSenha());
//        System.out.printf("Professor - email: %s - senha: %s\n", professor.getEmailCorporativo(), professorCadastroDTO.getSenha());
//        System.out.printf("Admin - email: %s - senha: %s\n", admin.getEmailCorporativo(), adminCadastroDTO.getSenha());
//
//        UI ui = new UI();
//        ui.run();
    }
}