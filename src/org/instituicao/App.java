package org.instituicao;

import org.instituicao.controller.*;
import org.instituicao.dto.*;
import org.instituicao.type.StatusTurma;
import org.instituicao.ui.AlunoView;
import org.instituicao.ui.UI;
import org.instituicao.util.data.DataUtils;

public class App {
    public static void main(String[] args) {
        // ### Inicializar controllers ###
        InstituicaoController instituicaoController = new InstituicaoController();
        CursoController cursoController = new CursoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        TurmaController turmaController = new TurmaController();
        AvaliacaoController avaliacaoController = new AvaliacaoController();
        AlunoController alunoController = new AlunoController();
        ProfessorController professorController = new ProfessorController();
        AdminController adminController = new AdminController();

        // ### Inicializar dados de exemplo ###
        InstituicaoDTO instituicao = instituicaoController.cadastrar(new InstituicaoCadastroDTO("Anima", "ulife.com", "ulife.com"));

        // * Inicializar curso e suas disciplinas
        CursoDTO curso = cursoController.cadastrar(new CursoCadastroDTO("Banco de dados: Big Data, Data Science & BI", instituicao.getId()));
        DisciplinaDTO disciplina1 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Programa��o de Solu��es Computacionais", "No��es de geometria an�litica e teoria de conjuntos �lgebra e L�gica booleana Algoritmos e suas representa��es L�gica de programa��o entrada, processamento, sa�da Constantes e vari�veis Estruturas de controle: sele��o e repeti��o Fun��es e procedimentos Passagem de par�metros por valor e refer�ncia Opera��es com vetores e matrizes Tipos abstratos de dados: pilhas, filas e listas Algoritmos de pesquisa e de ordena��o Paradigmas de Linguagens de Programa��o Programa��o orientada a objetos: classes, objetos, m�todos, atributos, construtores e m�todos de acesso e modificadores Encapsulamento, heran�a, abstra��o e polimorfismo Tratamento de exce��es Manipula��o de arquivos e conex�o com SGBD (Sistemas Gerenciadores de Banco de Dados) Bibliotecas de interfaces gr�ficas Linguagem SQL de banco de dados.", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina1.getId());
        DisciplinaDTO disciplina2 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Modelagem de software", "Introdu��o ao processo de desenvolvimento software Fundamentos de requisitos Tipos de requisitos (funcionais e n�ofuncionais) Modelagem e nota��o UML (Unified Modeling Language): diagramas estruturais e comportamentais (diagramas de casos de uso, de atividades e de classes) No��es b�sicas dos demais diagramas UML An�lise e projeto de banco de dados: relacional e n�orelacional Modelo entidaderelacionamento Modelo relacional e normaliza��o Modelo l�gico e f�sico de banco de dados Sistemas Gerenciadores de Banco de Dados (SGBDs) Implementa��o de transa��es de banco de dados (CRUD) Linguagem SQL", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina2.getId());
        DisciplinaDTO disciplina3 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Intelig�ncia Artificial", "Princ�pios �ticos, m�todos e t�cnicas para o desenvolvimento de sistemas baseados em conhecimento. Representa��o do conhecimento. Modelos simb�licos. Conexionistas. Heur�stica baseada em intelig�ncia social e evolutiva. Redes Neurais. �rvores de Decis�o. L�gica Fuzzy. Tipos de Aprendizado de M�quina: supervisionado, n�o supervisionado e aprendizado por refor�o. Redes Neurais Artificiais. Modelos de agrupamento (Mapas auto-organiz�veis e K-means). Reconhecimento de padr�es. Deep Learning: reconhecimento de imagem. Tend�ncias para o futuro da Intelig�ncia Artificial.", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina3.getId());
        DisciplinaDTO disciplina4 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Core curriculum", "Escolha do aluno", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina4.getId());

        TurmaDTO turma1 = turmaController.cadastrar(new TurmaCadastroDTO(disciplina1.getId()));
        turmaController.alterarStatus(turma1.getId(), StatusTurma.EM_ANDAMENTO);
        TurmaDTO turma2 = turmaController.cadastrar(new TurmaCadastroDTO(disciplina2.getId()));
        turmaController.alterarStatus(turma2.getId(), StatusTurma.EM_ANDAMENTO);
        TurmaDTO turma4 = turmaController.cadastrar(new TurmaCadastroDTO(disciplina4.getId()));
        turmaController.alterarStatus(turma4.getId(), StatusTurma.EM_ANDAMENTO);

        // * Inicializar usu�rios pr�-cadastrados
        AlunoCadastroDTO alunoCadastroDTO = new AlunoCadastroDTO("00012345678", "Davi Gramm Bauer", DataUtils.processarDataNascimento("27/09/2004"),"davi@mail.com", instituicao.getId(), "aluno123");
        AlunoDTO aluno = alunoController.cadastrar(alunoCadastroDTO);
        cursoController.adicionarAluno(curso.getId(), aluno.getMatricula());
        turmaController.adicionarAluno(turma1.getId(), aluno.getMatricula());
        turmaController.adicionarAluno(turma2.getId(), aluno.getMatricula());
        turmaController.adicionarAluno(turma4.getId(), aluno.getMatricula());

        FuncionarioCadastroDTO professorCadastroDTO = new FuncionarioCadastroDTO("01234567890", "John Doe", DataUtils.processarDataNascimento("01/01/1980"), "john@mail.com", instituicao.getId(), "professor123");
        FuncionarioDTO professor = professorController.cadastrar(professorCadastroDTO);
        turmaController.adicionarProfessor(turma1.getId(), professor.getMatricula());

        FuncionarioCadastroDTO adminCadastroDTO = new FuncionarioCadastroDTO("98765432100", "root", DataUtils.processarDataNascimento("25/12/1975"), "root@mail.com", instituicao.getId(), "admin");
        FuncionarioDTO admin = adminController.cadastrar(adminCadastroDTO);

        // * Cria��o de avalia��es por parte do professor
        AvaliacaoDTO avaliacao1_a1 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A1 - Avalia��o descritiva", turma1.getId()));
        AvaliacaoDTO avaliacao1_a2 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A2 - Avalia��o objetiva", turma1.getId()));
        AvaliacaoDTO avaliacao1_a3 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A3 - Cadastro e Gerenciamento de Alunos", turma1.getId()));
        AvaliacaoDTO avaliacao4_a1 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A1 - Avalia��o descritiva", turma4.getId()));
        AvaliacaoDTO avaliacao4_a2 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A2 - Avalia��o objetiva", turma4.getId()));
        AvaliacaoDTO avaliacao4_a3 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A3 - Artigo escrito em Japon�s", turma4.getId()));

        // * Entrega das avalia��es por parte do aluno
        avaliacaoController.entregarAvaliacao(avaliacao1_a1.getId(), aluno.getMatricula(), "Resposta exemplo da avalia��o A1.");
        avaliacaoController.entregarAvaliacao(avaliacao1_a2.getId(), aluno.getMatricula(), "Resposta exemplo da avalia��o A2.");
        avaliacaoController.entregarAvaliacao(avaliacao1_a3.getId(), aluno.getMatricula(), "Resposta exemplo da avalia��o A3.");
        avaliacaoController.entregarAvaliacao(avaliacao4_a1.getId(), aluno.getMatricula(), "Resposta em Japon�s da avalia��o A1.");
        avaliacaoController.entregarAvaliacao(avaliacao4_a2.getId(), aluno.getMatricula(), "Resposta em Japon�s da avalia��o A2.");
        avaliacaoController.entregarAvaliacao(avaliacao4_a3.getId(), aluno.getMatricula(), "Resposta em Japon�s da avalia��o A3.");

        // * Corre��o das entregas do aluno por parte do professor
        avaliacaoController.avaliarEntrega(avaliacao1_a1.getId(), aluno.getMatricula(), 8.5f);
        avaliacaoController.avaliarEntrega(avaliacao1_a2.getId(), aluno.getMatricula(), 9);
        avaliacaoController.avaliarEntrega(avaliacao1_a3.getId(), aluno.getMatricula(), 10);
        avaliacaoController.avaliarEntrega(avaliacao4_a1.getId(), aluno.getMatricula(), 1);
        avaliacaoController.avaliarEntrega(avaliacao4_a2.getId(), aluno.getMatricula(), 7);
        avaliacaoController.avaliarEntrega(avaliacao4_a3.getId(), aluno.getMatricula(), 3);

        // * Definir turmas j� finalizadas
        turmaController.alterarStatus(turma1.getId(), StatusTurma.CONCLUIDA);
        turmaController.alterarStatus(turma4.getId(), StatusTurma.CONCLUIDA);

        System.out.println("*** Logins pr�-cadastrados ***");
        System.out.printf("Aluno - email: %s - senha: %s\n", aluno.getEmailAcademico(), alunoCadastroDTO.getSenha());
        System.out.printf("Professor - email: %s - senha: %s\n", professor.getEmailCorporativo(), professorCadastroDTO.getSenha());
        System.out.printf("Admin - email: %s - senha: %s\n", admin.getEmailCorporativo(), adminCadastroDTO.getSenha());

        RelatorioAlunoDTO relatorio = alunoController.getRelatorio(100);
        new AlunoView().exibirRelatorio(relatorio);

//        UI ui = new UI();
//        ui.run();
    }
}