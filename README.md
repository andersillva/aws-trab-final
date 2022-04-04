# aws-trab-final

Nome: Anderson Machado da Silva

Matrícula: 142852


## Vídeo explicativo:

https://youtu.be/yN7DEDMKOO8


## Descrição da stack utilizada:

A implementação foi realizada em Java utilizando o framework Spring Boot. A implementação foi organizada da seguinte forma:

* Camada de persistência: Foi utilizado o padrão Repository com o ORM Hibernate. O banco de dados utilizado é o MySQL.

* Camada de serviço: Implementação de regras de negócio.

* Camada de controle: Utilizada para expor os endpoints.


Para encaminhamento de eventos, foi utilizada o Message Broker Kafka. O provisionamento dos serviços necessários foi realizado via Docker Compose.

Para proteção dos endpoints, foi utilizado o projeto Spring Security com uma implementação de JWT.

Para documentação da API, foi utilizado o padrão OpenAPI com Swagger.


## Eclipse e Lombok:

O projeto foi desenvolvido no IDE Eclipse versão 2021-06 (4.20.0) com o Lombok.

Para instalar o Lombok no Eclipse, seguir os passos definidos em https://projectlombok.org/setup/eclipse.


## Kafka:

O projeto utiliza o Kafka como message broker. Para subir o Kafka, existe o arquivo https://github.com/silvaandersonm/aws-trab-final/blob/master/docker-compose.yaml. Acessar a pasta raiz do projeto em um terminal e digitar os comando abaixo:

$ docker-compose build

$ docker-compose up


A interface para visualização das mensagens (aqhk.io) pode ser acessada em http://localhost:9080.


## OpenAPI:

A documentação da API (Swagger) pode ser acessada em http://localhost:8080/swagger-ui/index.html.

Foi postada uma versão offline da documentação em formato JSON em https://github.com/silvaandersonm/aws-trab-final/blob/master/api-docs/api-docs.json.


## Postman:

A collection do Postman também foi disponibilizada:

https://github.com/silvaandersonm/aws-trab-final/blob/master/postman-collection/aws-trab-final.postman_collection.json.
