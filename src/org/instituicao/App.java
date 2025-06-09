package org.instituicao;

import org.instituicao.controller.*;
import org.instituicao.dto.*;
import org.instituicao.type.StatusTurma;
import org.instituicao.ui.AlunoView;
import org.instituicao.ui.UI;
import org.instituicao.util.data.DataUtils;

public class App {
    public static void main(String[] args) {
        inicializarDados();

        UI ui = new UI();
        ui.run();
    }

    private static void inicializarDados() {
        InstituicaoController instituicaoController = new InstituicaoController();
        CursoController cursoController = new CursoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        TurmaController turmaController = new TurmaController();
        AvaliacaoController avaliacaoController = new AvaliacaoController();
        AlunoController alunoController = new AlunoController();
        ProfessorController professorController = new ProfessorController();
        AdminController adminController = new AdminController();

        // * Inicializar instituição
        InstituicaoDTO instituicao = instituicaoController.cadastrar(new InstituicaoCadastroDTO("Anima", "ulife.com", "ulife.com"));

        // * Inicializar curso e suas disciplinas (uma para cada status de disciplina [APROVADO, REPROVADO, CURSANDO, A_CURSAR])
        CursoDTO curso = cursoController.cadastrar(new CursoCadastroDTO("Banco de dados: Big Data, Data Science & BI", instituicao.getId()));
        DisciplinaDTO disciplina1 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Programação de Soluções Computacionais", "Noções de geometria análitica e teoria de conjuntos Álgebra e Lógica booleana Algoritmos e suas representações Lógica de programação entrada, processamento, saída Constantes e variáveis Estruturas de controle: seleção e repetição Funções e procedimentos Passagem de parâmetros por valor e referência Operações com vetores e matrizes Tipos abstratos de dados: pilhas, filas e listas Algoritmos de pesquisa e de ordenação Paradigmas de Linguagens de Programação Programação orientada a objetos: classes, objetos, métodos, atributos, construtores e métodos de acesso e modificadores Encapsulamento, herança, abstração e polimorfismo Tratamento de exceções Manipulação de arquivos e conexão com SGBD (Sistemas Gerenciadores de Banco de Dados) Bibliotecas de interfaces gráficas Linguagem SQL de banco de dados.", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina1.getId());
        DisciplinaDTO disciplina2 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Modelagem de software", "Introdução ao processo de desenvolvimento software Fundamentos de requisitos Tipos de requisitos (funcionais e nãofuncionais) Modelagem e notação UML (Unified Modeling Language): diagramas estruturais e comportamentais (diagramas de casos de uso, de atividades e de classes) Noções básicas dos demais diagramas UML Análise e projeto de banco de dados: relacional e nãorelacional Modelo entidaderelacionamento Modelo relacional e normalização Modelo lógico e físico de banco de dados Sistemas Gerenciadores de Banco de Dados (SGBDs) Implementação de transações de banco de dados (CRUD) Linguagem SQL", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina2.getId());
        DisciplinaDTO disciplina3 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Inteligência Artificial", "Princípios éticos, métodos e técnicas para o desenvolvimento de sistemas baseados em conhecimento. Representação do conhecimento. Modelos simbólicos. Conexionistas. Heurística baseada em inteligência social e evolutiva. Redes Neurais. Árvores de Decisão. Lógica Fuzzy. Tipos de Aprendizado de Máquina: supervisionado, não supervisionado e aprendizado por reforço. Redes Neurais Artificiais. Modelos de agrupamento (Mapas auto-organizáveis e K-means). Reconhecimento de padrões. Deep Learning: reconhecimento de imagem. Tendências para o futuro da Inteligência Artificial.", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina3.getId());
        DisciplinaDTO disciplina4 = disciplinaController.cadastrar(new DisciplinaCadastroDTO("Core curriculum", "Escolha do aluno", instituicao.getId()));
        cursoController.adicionarDisciplina(curso.getId(), disciplina4.getId());

        // * Inicializar turmas (somente 3 para manter a situação de uma das disciplinas como "A_CURSAR" na visão do aluno)
        TurmaDTO turma1 = turmaController.cadastrar(new TurmaCadastroDTO(disciplina1.getId()));
        turmaController.alterarStatus(turma1.getId(), StatusTurma.EM_ANDAMENTO);
        TurmaDTO turma2 = turmaController.cadastrar(new TurmaCadastroDTO(disciplina2.getId()));
        turmaController.alterarStatus(turma2.getId(), StatusTurma.EM_ANDAMENTO);
        TurmaDTO turma4 = turmaController.cadastrar(new TurmaCadastroDTO(disciplina4.getId()));
        turmaController.alterarStatus(turma4.getId(), StatusTurma.EM_ANDAMENTO);

        // * Inicializar admin
        FuncionarioCadastroDTO adminCadastroDTO = new FuncionarioCadastroDTO("98765432100", "root", DataUtils.processarDataNascimento("25/12/1975"), "root@mail.com", instituicao.getId(), "admin");
        FuncionarioDTO admin = adminController.cadastrar(adminCadastroDTO);

        // * Inicializar professor, designando apenas 1 turma ("Programação de Soluções Computacionais")
        FuncionarioCadastroDTO professorCadastroDTO = new FuncionarioCadastroDTO("01234567890", "John Doe", DataUtils.processarDataNascimento("01/01/1980"), "john@mail.com", instituicao.getId(), "professor123");
        FuncionarioDTO professor = professorController.cadastrar(professorCadastroDTO);
        turmaController.adicionarProfessor(turma1.getId(), professor.getMatricula());
        turmaController.adicionarProfessor(turma2.getId(), professor.getMatricula());

        // * Inicializar alunos, adicionando no curso e turmas já cadastradas
        AlunoCadastroDTO alunoCadastroDTO = new AlunoCadastroDTO("00012345678", "Davi Gramm Bauer", DataUtils.processarDataNascimento("27/09/2004"),"davi@mail.com", instituicao.getId(), "aluno123");
        AlunoDTO aluno1 = alunoController.cadastrar(alunoCadastroDTO);
        cursoController.adicionarAluno(curso.getId(), aluno1.getMatricula());
        turmaController.adicionarAluno(turma1.getId(), aluno1.getMatricula());
        turmaController.adicionarAluno(turma2.getId(), aluno1.getMatricula());
        turmaController.adicionarAluno(turma4.getId(), aluno1.getMatricula());

        AlunoCadastroDTO alunoCadastroDTO2 = new AlunoCadastroDTO("01122233345", "João da Silva", DataUtils.processarDataNascimento("08/09/1999"),"jaosilva@mail.com", instituicao.getId(), "123");
        AlunoDTO aluno2 = alunoController.cadastrar(alunoCadastroDTO2);
        cursoController.adicionarAluno(curso.getId(), aluno2.getMatricula());
        turmaController.adicionarAluno(turma2.getId(), aluno2.getMatricula());

        // * Criação de avaliações por parte do professor
        AvaliacaoDTO avaliacao1_a1 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A1 - Avaliação descritiva", turma1.getId()));
        AvaliacaoDTO avaliacao1_a2 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A2 - Avaliação objetiva", turma1.getId()));
        AvaliacaoDTO avaliacao1_a3 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A3 - Cadastro e Gerenciamento de Alunos", turma1.getId()));
        AvaliacaoDTO avaliacao2_a1 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A1 - Avaliação descritiva", turma2.getId()));
        AvaliacaoDTO avaliacao2_a2 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A2 - Avaliação objetiva", turma2.getId()));
        AvaliacaoDTO avaliacao2_a3 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A3 - Diagramação de aplicação", turma2.getId()));
        AvaliacaoDTO avaliacao4_a1 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A1 - Avaliação descritiva", turma4.getId()));
        AvaliacaoDTO avaliacao4_a2 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A2 - Avaliação objetiva", turma4.getId()));
        AvaliacaoDTO avaliacao4_a3 = avaliacaoController.cadastrar(new AvaliacaoCadastroDTO("A3 - Artigo escrito em Japonês", turma4.getId()));

        // * Entrega das avaliações por parte do aluno
        avaliacaoController.entregarAvaliacao(avaliacao1_a1.getId(), aluno1.getMatricula(), "Resposta exemplo da avaliação A1 de Programação de Soluções Computacionais.");
        avaliacaoController.entregarAvaliacao(avaliacao1_a2.getId(), aluno1.getMatricula(), "Resposta exemplo da avaliação A2 de Programação de Soluções Computacionais.");
        avaliacaoController.entregarAvaliacao(avaliacao1_a3.getId(), aluno1.getMatricula(), "Resposta exemplo da avaliação A3 de Programação de Soluções Computacionais.");
        avaliacaoController.entregarAvaliacao(avaliacao4_a1.getId(), aluno1.getMatricula(), "Resposta em Japonês da avaliação A1 de core curriculum.");
        avaliacaoController.entregarAvaliacao(avaliacao4_a2.getId(), aluno1.getMatricula(), "Resposta em Japonês da avaliação A2 de core curriculum.");
        avaliacaoController.entregarAvaliacao(avaliacao4_a3.getId(), aluno1.getMatricula(), "Resposta em Japonês da avaliação A3 de core curriculum.");
        avaliacaoController.entregarAvaliacao(avaliacao2_a1.getId(), aluno1.getMatricula(), "Resposta aluno1 da avaliação A1 de Modelagem de Software.");
        avaliacaoController.entregarAvaliacao(avaliacao2_a1.getId(), aluno2.getMatricula(), "Resposta aluno2 da avaliação A1 de Modelagem de Software.");

        // * Correção das entregas do aluno por parte do professor
        avaliacaoController.avaliarEntrega(avaliacao1_a1.getId(), aluno1.getMatricula(), 8.5f);
        avaliacaoController.avaliarEntrega(avaliacao1_a2.getId(), aluno1.getMatricula(), 9);
        avaliacaoController.avaliarEntrega(avaliacao1_a3.getId(), aluno1.getMatricula(), 10);
        avaliacaoController.avaliarEntrega(avaliacao4_a1.getId(), aluno1.getMatricula(), 1);
        avaliacaoController.avaliarEntrega(avaliacao4_a2.getId(), aluno1.getMatricula(), 7);
        avaliacaoController.avaliarEntrega(avaliacao4_a3.getId(), aluno1.getMatricula(), 3);

        // * Definir turmas já finalizadas
        turmaController.alterarStatus(turma1.getId(), StatusTurma.CONCLUIDA);
        turmaController.alterarStatus(turma4.getId(), StatusTurma.CONCLUIDA);

        System.out.println("*** Logins pré-cadastrados ***");
        System.out.printf("* Admin     - email: %s - senha: %s\n", admin.getEmailCorporativo(), adminCadastroDTO.getSenha());
        System.out.printf("* Professor - email: %s - senha: %s\n", professor.getEmailCorporativo(), professorCadastroDTO.getSenha());
        System.out.printf("* Aluno1    - email: %s - senha: %s\n", aluno1.getEmailAcademico(), alunoCadastroDTO.getSenha());
        System.out.printf("* Aluno2    - email: %s - senha: %s\n", aluno2.getEmailAcademico(), alunoCadastroDTO2.getSenha());
        System.out.println("******************************");
    }
}