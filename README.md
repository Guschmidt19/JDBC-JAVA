# Cap 11 – Manipulação de Banco de Dados em Java | JDBC + DAO

Atividade prática do Capítulo 11 do curso de Java na FIAP, com foco em **persistência de dados com JDBC** utilizando o padrão de projeto **DAO (Data Access Object)**. O projeto conecta a aplicação Java ao banco de dados Oracle para realizar operações CRUD completas nas entidades do sistema Fintech.

## Tecnologias

- Java 17+
- Maven (gerenciador de dependências)
- JDBC (Java Database Connectivity)
- Oracle Database
- Padrão DAO

## Estrutura do Projeto

```
src/main/java/br/com/fiap/
├── dao/
│   ├── ContaBancariaDao.java   # CRUD de contas bancárias
│   ├── DespesaDao.java         # CRUD de despesas
│   ├── DividaDao.java          # CRUD de dívidas
│   ├── InvestimentoDao.java    # CRUD de investimentos
│   ├── MetaDao.java            # CRUD de metas financeiras
│   └── ReceitaDao.java         # CRUD de receitas
├── model/
│   ├── ContaBancaria.java      # Entidade conta bancária
│   ├── Despesa.java            # Entidade despesa
│   ├── Divida.java             # Entidade dívida
│   ├── Investimento.java       # Entidade investimento
│   ├── Meta.java               # Entidade meta financeira
│   └── Receita.java            # Entidade receita
├── infra/
│   └── ConnectionFactory.java  # Gerenciamento de conexão com o banco
├── view/
│   ├── TesteContaBancaria.java
│   ├── TesteDespesa.java
│   ├── TesteDivida.java
│   ├── TesteInvestimento.java
│   ├── TesteMeta.java
│   └── TesteReceita.java
└── main.java                   # Ponto de entrada principal
```

## Funcionalidades

- Conexão com Oracle Database via `ConnectionFactory` (padrão Singleton)
- **ContaBancaria**: listar todas, inserir (banco, agência, número, tipo, saldo, usuário)
- **Despesa**: operações CRUD com categoria e valor
- **Dívida**: controle de dívidas com valor e status
- **Investimento**: registro de aportes financeiros
- **Meta**: gerenciamento de metas financeiras com prazo e valor-alvo
- **Receita**: registro de entradas financeiras

## Como Configurar e Executar

1. Clone ou abra o projeto no IntelliJ IDEA / Eclipse
2. Configure a conexão com o banco em `ConnectionFactory.java` (URL, usuário e senha Oracle)
3. Execute o script DDL de criação das tabelas no Oracle
4. Rode qualquer classe `Teste*.java` na pasta `view/` para testar as operações

### Dependência Maven (pom.xml)
```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
</dependency>
```

## Conceitos Abordados

- Conexão JDBC com banco de dados relacional
- Padrão de projeto DAO para separação de camadas
- `PreparedStatement` para consultas parametrizadas (prevenção de SQL Injection)
- `ResultSet` para leitura dos resultados
- Gerenciamento de conexões com `try-with-resources`
- Mapeamento objeto-relacional manual
- Organização em camadas: model, dao, infra, view
