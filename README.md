Projeto criado para uma vaga de estagio na empresa Cilia Tecnologia.

# Visão geral

O projeto e uma aplicação back-end com objetivo de registrar vendas atraves de API utlizando  [Spring Boot](https://projects.spring.io/spring-boot)

## Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) 
- [Spring Data](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)
- [Docker](https://www.docker.com/)
- [Postgres](https://www.postgresql.org/)
- [MapStruct](https://mapstruct.org/)
- 

# Setup da aplicação (local)

## Pré-Requisito

```
Java 17
Docker
```


## Instalação da aplicação

Primeiramente, faça o clone do repositório:
```
git clone https://github.com/gustavocleite/sales-project.git
```
Feito isso, acesse o projeto:
```
cd sales-project-backend
```

É preciso executar o docker compose para executar o projeto.
```bash
docker compose up
```

Pronto. A aplicação está disponível em http://localhost:8080

# APIs

O projeto disponibiliza algumas APIs em 3 contextos diferentes: Client, Product e sale, onde utilizam o padrão Rest de comunicação, produzindo e consumindo arquivos no formato JSON.

Apos o projeto iniciado, acesse a URL: http://localhost:8080/swagger-ui/index.html 
