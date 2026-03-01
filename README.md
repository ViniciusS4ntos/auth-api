![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.3-brightgreen?style=for-the-badge&logo=springboot)
![Redis](https://img.shields.io/badge/Redis-7-orange?style=for-the-badge&logo=redis)
![JWT](https://img.shields.io/badge/JWT-JSON%20Web%20Token-red?style=for-the-badge&logo=jwt)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)

# Auth-Api

**Auth-Api** é um **microserviço de autenticação e segurança** que utiliza **JWT** para gerenciamento de tokens. Ele salva o token emitido após o login no **Redis** e armazena os dados do usuário no **PostgreSQL**, com senhas criptografadas. O projeto é containerizado com **Docker** e inclui `Dockerfile` e `docker-compose` para facilitar a execução em qualquer ambiente Docker.  

---

## Tecnologias Utilizadas

- Java 17 / Spring Boot  
- JWT (JSON Web Token)  
- Redis (para armazenamento de tokens)  
- PostgreSQL (para persistência de usuários)  
- Docker / Docker Compose  

---

## Funcionalidades

- Autenticação de usuários via JWT  
- Armazenamento de tokens no Redis  
- Criptografia de senhas no PostgreSQL  
- Containerização do microserviço para fácil deployment  

---

## Pré-requisitos

- Docker e Docker Compose instalados na máquina  
- Redis e PostgreSQL criados e configurados  
  - Configure os dados de conexão no arquivo `application.properties`  

---

## Rodando o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/ViniciusS4ntos/auth-api.git
cd auth-api

Configure application.properties com os dados do PostgreSQL e Redis:

#Configure seu postgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=usuario
spring.datasource.password=senha

#Configure seu Redis
spring.redis.host=localhost
spring.redis.port=6379

Build e rode o container:

docker-compose up --build

Lembre-se: o Redis e o PostgreSQL devem estar rodando e configurados antes de iniciar a aplicação.

Endpoints Principais

POST /usuario – Cria um cadastro no banco SQL

POST /usuario/login - Faz login com o cadastro feito e voltar um token de security

GET /usuario - Usando o token gerado pelo /usuario/login agoora pode visualizar todos os cadastros, claro com a senha criptografada

Estrutura do Projeto
auth-api/
├── src/
│   ├── main/
│   │   ├── java/com/vinicius/auth_api/
│   │   │   ├── business/
│   │   │   │   ├── RedisService.java
│   │   │   │   └── UsuarioService.java
│   │   │   ├── controller/
│   │   │   │   └── UsuarioController.java
│   │   │   ├── infrastructure/
│   │   │   │   ├── entity/
│   │   │   │   │   └── Usuario.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── EmailExistenteException.java
│   │   │   │   │   └── InvalidTokenException.java
│   │   │   │   └── repository/
│   │   │   │       └── UsuarioRepository.java
│   │   │   └── security/
│   │   │       └── AuthApiApplication.java
│   │   └── resources/
│   │       └── application.properties
├── Dockerfile
├── docker-compose.yml
└── README.md
Observações

Para qualquer alteração na configuração do Redis ou PostgreSQL, atualize o application.properties.

A aplicação está pronta para ser executada em qualquer container Docker.
