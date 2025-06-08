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

        DisciplinaDTO disciplina = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Programação de Soluções Computacionais", "Noções de geometria análitica e teoria de conjuntos Álgebra e Lógica booleana Algoritmos e suas representações Lógica de programação entrada, processamento, saída Constantes e variáveis Estruturas de controle: seleção e repetição Funções e procedimentos Passagem de parâmetros por valor e referência Operações com vetores e matrizes Tipos abstratos de dados: pilhas, filas e listas Algoritmos de pesquisa e de ordenação Paradigmas de Linguagens de Programação Programação orientada a objetos: classes, objetos, métodos, atributos, construtores e métodos de acesso e modificadores Encapsulamento, herança, abstração e polimorfismo Tratamento de exceções Manipulação de arquivos e conexão com SGBD (Sistemas Gerenciadores de Banco de Dados) Bibliotecas de interfaces gráficas Linguagem SQL de banco de dados.", instituicao.getId()));

        CursoDTO curso = cursoController.cadastrar(new CursoCadastroDTO("Banco de dados: Big Data, Data Science & BI", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina.getId());

        TurmaDTO turma = turmaController.cadastrar(new TurmaCadastroDTO(disciplina.getId()));
        turmaController.alterarStatus(turma.getId(), StatusTurma.EM_ANDAMENTO);

        AvaliacaoDTO avaliacao = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A3 - Cadastro e Gerenciamento de Alunos", turma.getId()));

        // Inicializar usuários pré-cadastrados
        AlunoCadastroDTO alunoCadastroDTO = new AlunoCadastroDTO("00012345678", "Davi Gramm Bauer", DataUtils.processarDataNascimento("27/09/2004"),"davi@mail.com", instituicao.getId(), "aluno123");
        AlunoDTO aluno = alunoController.cadastrar(alunoCadastroDTO);
        cursoController.adicionarAluno(curso.getId(), aluno.getMatricula());
        turmaController.adicionarAluno(turma.getId(), aluno.getMatricula());
        avaliacaoController.entregarAvaliacao(avaliacao.getId(), aluno.getMatricula(), "Resposta exemplo da avaliação.");

        FuncionarioCadastroDTO professorCadastroDTO = new FuncionarioCadastroDTO("01234567890", "John Doe", DataUtils.processarDataNascimento("01/01/1980"), "john@mail.com", instituicao.getId(), "professor123");
        FuncionarioDTO professor = professorController.cadastrar(professorCadastroDTO);
        turmaController.adicionarProfessor(turma.getId(), professor.getMatricula());
        avaliacaoController.avaliarEntrega(avaliacao.getId(), aluno.getMatricula(), 9.5f);

        FuncionarioCadastroDTO adminCadastroDTO = new FuncionarioCadastroDTO("98765432100", "root", DataUtils.processarDataNascimento("25/12/1975"), "root@mail.com", instituicao.getId(), "admin");
        FuncionarioDTO admin = adminController.cadastrar(adminCadastroDTO);



        turmaController.alterarStatus(turma.getId(), StatusTurma.CONCLUIDA);

        RelatorioAlunoDTO relatorio = alunoController.getRelatorio(100);
        new AlunoView().exibirRelatorio(relatorio);


//        System.out.println("*** Logins pré-cadastrados ***");
//        System.out.printf("Aluno - email: %s - senha: %s\n", aluno.getEmailAcademico(), alunoCadastroDTO.getSenha());
//        System.out.printf("Professor - email: %s - senha: %s\n", professor.getEmailCorporativo(), professorCadastroDTO.getSenha());
//        System.out.printf("Admin - email: %s - senha: %s\n", admin.getEmailCorporativo(), adminCadastroDTO.getSenha());
//
//        UI ui = new UI();
//        ui.run();
    }
}