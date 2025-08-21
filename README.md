# Projeto Java + SQL

Este projeto foi desenvolvido em **Java** com integração a **banco de dados SQL**, com o objetivo de simplificar operações de cadastro e consulta de informações. Ele demonstra conceitos de **CRUD (Create, Read, Update, Delete)** e boas práticas de organização de código em camadas.

## 📌 Funcionalidades
- Conexão com banco de dados relacional (MySQL, PostgreSQL ou outro compatível via JDBC).
- Criação e manipulação de tabelas.
- Inserção de registros.
- Consulta de dados.
- Atualização e exclusão de registros.
- Camadas **DAO**, **Model** e **Main**.

## 🛠️ Tecnologias utilizadas
- **Java 11+**
- **JDBC**
- **MySQL** (padrão; adaptável a outros bancos)
- **SQL**
- IDE recomendada: IntelliJ IDEA / Eclipse / VS Code

## ⚙️ Configuração do Banco de Dados
1) Crie o banco e a tabela (exemplo MySQL):
```sql
CREATE DATABASE escola;
USE escola;

CREATE TABLE aluno (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  idade INT NOT NULL
);
```

2) Configure a classe de conexão (ex.: `ConnectionFactory.java`):
```java
private static final String URL = "jdbc:mysql://localhost:3306/escola";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha";
```

## ▶️ Como executar
1) Compilar:
```bash
javac -d bin src/**/*.java
```
2) Executar a classe principal:
```bash
java -cp bin main.App
```

## 📤 Exemplos de uso (DAO)
**Inserir:**
```java
Aluno aluno = new Aluno("Maria", 20);
alunoDAO.inserir(aluno);
```

**Listar:**
```java
List<Aluno> alunos = alunoDAO.listar();
for (Aluno a : alunos) {
    System.out.println(a.getNome());
}
```

**Atualizar / Excluir:**
```java
aluno.setId(1);
aluno.setNome("Maria Silva");
alunoDAO.atualizar(aluno);

alunoDAO.excluir(1);
```

## ✅ Requisitos
- JDK 11+
- MySQL (ou outro banco compatível)
- Driver JDBC (no classpath via `lib/` ou Maven/Gradle)

## 🧪 Testes (exemplo JUnit)
```java
@Test
public void deveInserirAluno() {
    Aluno aluno = new Aluno("Teste", 18);
    alunoDAO.inserir(aluno);
    assertNotNull(aluno.getId());
}
```

## 📄 Licença
Distribuído sob a **MIT License**. Você pode usar, copiar, modificar e distribuir este software desde que mantenha o aviso de copyright e a licença.
