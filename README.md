# Teste Turing - Itaú

Aplicação desenvolvida para o Processo Seletivo do Programa de Estágio do Banco Itaú.
- Sistema que simula as transações bancárias — PIX, TED, DOC
- Orientações fornecidas: [README.md](https://github.com/itau-canais-estag/teste-turing/blob/main/README.md)


## Requisitos

- Java 11+
- Maven 3+
- Mysql 8+

## Setup

### Clonar a aplicação

```
https://github.com/swy-326/TesteTuringItau.git
```

### Configurar MySql

No arquivo `src\main\resources\application.properties`, configure o username e password

```
# MySQL
spring.datasource.username=root
spring.datasource.password=
```

### Build e Run

```
mvn clean spring-boot:run
```

A aplicação será iniciada em http://localhost:8080


## Desenvolvimento

### Modelagem de Banco de Dados

![Modelagem de Banco de Dados](db.png "Banco de Dados")

### Pontos a melhorar / implementar

- Aumento da cobertura de testes
- Tratamento de exceções
- Utilização de Docker
- Melhor controle das transações de BD (rollback, @Transactional)
- Enriquecer a lógica dos services
- Organização dos pacotes orientada à funcionalidade
- TDD