![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen?style=for-the-badge&logo=springboot)
![Redis](https://img.shields.io/badge/Redis-7-orange?style=for-the-badge&logo=redis)
![JWT](https://img.shields.io/badge/JWT-Authentication-red?style=for-the-badge&logo=jsonwebtokens)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue?style=for-the-badge&logo=docker)
![Gradle](https://img.shields.io/badge/Gradle-Build_Tool-black?style=for-the-badge&logo=gradle)

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:141E30,50:243B55,100:0F2027&height=220&section=header&text=Auth%20API&fontSize=45&fontColor=ffffff&animation=fadeIn" />
</p>

<p align="center">
  <b>Microserviço de autenticação utilizando JWT, Redis e PostgreSQL com Spring Boot.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/github/stars/ViniciusS4ntos/auth-api?style=social" />
  <img src="https://img.shields.io/github/forks/ViniciusS4ntos/auth-api?style=social" />
  <img src="https://img.shields.io/github/issues/ViniciusS4ntos/auth-api" />
</p>

---

# Auth API

**Auth API** é um microserviço de autenticação e segurança desenvolvido com **Java + Spring Boot**.

O projeto utiliza **JWT** para autenticação de usuários, **Redis** para armazenamento de tokens e **PostgreSQL** para persistência de dados e senhas criptografadas.

A aplicação também possui suporte para execução via **Docker** e estrutura organizada em camadas.

---

# Tecnologias Utilizadas

- Java 17  
- Spring Boot 3  
- Spring Security  
- JWT (JSON Web Token)  
- Redis  
- PostgreSQL  
- Docker / Docker Compose  
- Gradle  
- Lombok  

---

# Funcionalidades

- Cadastro de usuários  
- Login de usuários  
- Autenticação via JWT  
- Armazenamento de tokens no Redis  
- Criptografia de senhas  
- Rotas protegidas  
- Tratamento de exceções  
- Containerização com Docker  

---

# Pré-requisitos

- Java 17 instalado  
- Docker e Docker Compose instalados  
- PostgreSQL configurado  
- Redis configurado  

---

# Rodando o Projeto

## 1. Clone o repositório

```bash
git clone https://github.com/ViniciusS4ntos/auth-api.git
cd auth-api
```

---

## 2. Configure o `application.properties`

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=usuario
spring.datasource.password=senha

# Redis
spring.redis.host=localhost
spring.redis.port=6379
```

---

## 3. Execute os containers

```bash
docker-compose up --build
```

---

## 4. Rodar manualmente

### Linux/Mac

```bash
./gradlew bootRun
```

### Windows

```bash
gradlew.bat bootRun
```

---

# Autenticação JWT

A API utiliza autenticação baseada em JWT.

Fluxo:

1. Usuário realiza login  
2. A API gera um token JWT  
3. O token é salvo no Redis  
4. O cliente envia o token no header Authorization  

Exemplo:

```http
Authorization: Bearer SEU_TOKEN
```

---

# Endpoints Principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/usuario` | Cria um usuário |
| POST | `/usuario/login` | Realiza login |
| GET | `/usuario` | Lista usuários autenticados |

---

# Estrutura do Projeto

```text
Directory structure:
└── viniciuss4ntos-auth-api/
    ├── README.md
    ├── docker-compose.yml
    ├── Dockerfile
    ├── gradlew
    ├── gradlew.bat
    ├── gradle/
    │   └── wrapper/
    │       └── gradle-wrapper.properties
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/
    │       │       └── vinicius/
    │       │           └── auth_api/
    │       │               ├── AuthApiApplication.java
    │       │               ├── business/
    │       │               │   ├── EmailService.java
    │       │               │   ├── RedisService.java
    │       │               │   └── UsuarioService.java
    │       │               ├── controller/
    │       │               │   ├── UsuarioController.java
    │       │               │   ├── converte/
    │       │               │   │   └── UsuarioConverte.java
    │       │               │   └── DTO/
    │       │               │       └── UsuarioDTO.java
    │       │               ├── infrastructure/
    │       │               │   ├── entity/
    │       │               │   │   ├── PasswordResetToken.java
    │       │               │   │   └── Usuario.java
    │       │               │   ├── exception/
    │       │               │   │   ├── EmailExistenteException.java
    │       │               │   │   └── InvalidTokenException.java
    │       │               │   └── repository/
    │       │               │       ├── PasswordResetTokenRepository.java
    │       │               │       └── UsuarioRepository.java
    │       │               └── security/
    │       │                   ├── JwtRequestFilter.java
    │       │                   ├── JwtUtil.java
    │       │                   ├── SecurityConfig.java
    │       │                   └── UserDetailsServiceImpl.java
    │       └── resources/
    │           └── application.properties
    └── .github/
        └── workflows/
            └── gradle.yml

```

---

# Segurança

O projeto possui:

- Spring Security  
- JWT Authentication  
- Password Encoder  
- Redis para sessão/token  
- Rotas protegidas  
- Tratamento de autenticação  

---

# Docker

## Subir containers

```bash
docker-compose up -d
```

## Derrubar containers

```bash
docker-compose down
```

---

# Autor

Desenvolvido por Edson Vinicius.
