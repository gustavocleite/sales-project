Projeto criado para uma vaga de estagio na empresa Cilia Tecnologia.

# Visão geral

O projeto e uma aplicação back-end com objetivo de registrar vendas atraves de API utlizando  [Spring Boot](https://projects.spring.io/spring-boot)

## Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) é uma ferramenta que simplifica a configuração e execução de aplicações Java stand-alone,  com conceitos de dependências “starters”, auto configuração e servlet container embutidos é proporcionado uma grande produtividade desde o start-up da aplicação até sua ida a produção.
- [Spring Data](https://spring.io/projects/spring-data-jpa) JPA é uma extensão do Spring que simplifica a interação com bancos de dados relacionais usando JPA. Ele oferece repositórios automatizados para operações CRUD, reduzindo a necessidade de escrever código boilerplate e facilitando o gerenciamento de dados em aplicações Java.

# Setup da aplicação (local)

## Pré-Requisito

```
Java 17
Docker version 26.1.3
PostgreSQL 42.7.3
Maven 3.9.6 
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
É preciso executar o docker-compose para subir os containers
```bash
docker-compose up
```

Pronto. A aplicação está disponível em http://localhost:8080

# APIs

O projeto disponibiliza algumas APIs em 3 contextos diferentes: Client, Product e sale, onde utilizam o padrão Rest de comunicação, produzindo e consumindo arquivos no formato JSON.

Apos o projeto iniciado, acesse a URL: http://localhost:8080/swagger-ui/index.html 
