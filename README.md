# aws-trab-final

Eclipse e Lombok:
O projeto foi desenvolvido no IDE Eclipse versão 2021-06 (4.20.0).
O projeto utiliza o Lombok. Para instalar o Lombok no Eclipse, seguir os passos definidos em https://projectlombok.org/setup/eclipse.

Kafka:
O projeto utiliza o Kafka como message broker. Para subir o Kafka, existe um arquivo docker-compose.yaml. Acessar a pasta raiz do projeto em um terminal e digitar os comando abaixo:
$ docker-compose build
$ docker-compose up

A interface para visualização das mensagens (aqhk.io) pode ser acessada em http://localhost:9080.

OpenAPI:
A documentação da API (Swagger) pode ser acessada em http://localhost:8080/swagger-ui/index.html.