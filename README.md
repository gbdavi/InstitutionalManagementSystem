
# 🎓 Sistema de Cadastro e Gerenciamento de Alunos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge\&logo=java\&logoColor=white)
![MVC](https://img.shields.io/badge/Architecture-MVC-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge)

## 📌 Descrição do Projeto

Este projeto foi desenvolvido individualmente como parte da disciplina de **Programação de Soluções Computacionais** no curso de graduação em `Banco de Dados: Data Science, Big Data & BI`, com o objetivo de criar uma aplicação em Java capaz de **cadastrar, organizar e analisar o desempenho acadêmico de alunos**, conforme [escopo](/docs/Escopo.pdf).

Mais do que um simples sistema de cadastro, o projeto foi estruturado de forma **modular e expansível**, pensando em futuras integrações como:

* Envio automático de boletins por e-mail
* Geração de relatórios avançados
* Integração com banco de dados
* Criação de uma interface web para usuários

A arquitetura do projeto foi baseada no padrão **MVC (Model-View-Controller)** e utiliza boas práticas de **programação orientada a objetos**, visando clareza, organização e facilidade de manutenção.

---

## 🎯 Objetivos

* Permitir o cadastro de alunos com suas respectivas notas
* Calcular a média de desempenho de cada aluno
* Verificar se o aluno está **Aprovado** (média ≥ 7) ou **Reprovado**
* Apresentar um relatório final no terminal com todos os dados organizados
* Estruturar o código com foco em **reutilização**, **extensibilidade** e **preparação para integrações futuras**

---

## 🧠 Funcionalidades

* Cadastro de alunos, professores e administradores
* Lógica de turmas, cursos e disciplinas
* Registro de avaliações e entregas
* Cálculo automático de médias e definição de situação do aluno
* Relatório geral dos alunos da instituição
* Restrição de funcionalidades por tipo de usuário (professor, aluno, administrador)

---

## 🧱 Estrutura do Projeto (MVC)

```
src/
├── controller/           # Controladores da aplicação
├── model/
│   ├── dto/              # Objetos de transferência de dados
│   ├── entity/           # Representação das entidades do sistema
│   ├── repository/       # Simulação de persistência de dados
│   ├── service/          # Regras de negócio
│   ├── type/             # Tipos auxiliares (enums, etc.)
│   └── util/             # Classes utilitárias
└── view/
    └── ui/               # Interface via console (terminal)
```

---

## 🛠️ Tecnologias Utilizadas

* **Java 24**
* **IntelliJ IDEA**
* Estrutura **MVC**
* Programação Orientada a Objetos (POO)
* Entrada e saída via **console**

---

## 📖 Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/gbdavi/InstitutionalManagementSystem.git
   ```
2. Abra o projeto no IntelliJ IDEA (ou outra IDE compatível com Java)
3. Execute a classe principal localizada em `src/org/instituicao`
4. Siga as instruções no console para navegar pelo sistema

---

## 📄 Documentação Técnica


A lógica foi construída com foco em expansão futura, explicada com mais detalhes na [documentação](/docs/DocumentacaoProjeto.pdf). Já é possível:

* Reutilizar os controladores para expor endpoints REST
* Substituir o repositório atual por um banco de dados real com mínimo impacto
* Criar novas funcionalidades como "Coordenador de Curso" com pouco esforço

Além disso, a separação clara de responsabilidades entre as camadas facilita testes unitários, manutenções e evoluções do sistema.

---

## 🌟 Possibilidades Futuras

* Integração com banco de dados (MySQL, PostgreSQL)
* Interface gráfica web (Spring Boot + Angular/React)
* API REST para consumo por front-ends
* Envio de boletins automáticos por e-mail
* Autenticação de usuários e controle de acesso
