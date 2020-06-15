# Acert-challenge
Criação de  API no padrão RESTFUL que permita o usuário obter resultados de conversão de graus celsius para fahrenheit e vice-versa mantendo as consultas realizadas num histórico, sendo este histórico acessível através de outra api.


### Pré requisitos

* Docker
* Docker CE
* Docker Compose

### Tecnologias

- Spring Boot 2.2.7
- Lombok 1.18
- Liquibase 3.9.0
- Java 8
- Jpa 2.2
- Postgres 9.6
#### Executando
- Baixe o projeto e extraia, depois entre na pasta criada chamada, acert-challenge-master.
- Dentro da pasta execute o comando abaixo:  
```
sudo docker-compose up --build -d
```
#### Instalando Docker CE
```
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io
```
#### Testando API
Após os passos anteriores vc está apto acessar a API:
- Para testar a API poder ser usado tanto o [Postman](https://www.postman.com/) ou qualquer outro cliente de sua preferência.

- Outra opção é acessar o console gráfico do [Swagger](http://localhost:8082/acert/celsius-fahrenheit/swagger-ui.html) onde está 
a documentação da API