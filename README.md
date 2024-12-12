# API - Projeto AILU

Projeto Desenvilvido em Django Rest Framework

## Passo a passo para execução do projeto (ambiente de desenvolvimento)

Requisitos para execução

Com docker:

* [Docker](https://docs.docker.com/get-docker/)
* [Docker Compose](https://docs.docker.com/compose/install/)
* [Make](https://stat545.com/make-windows.html) (opcional)

Sem docker:

* [Python](https://www.python.org/downloads/)
* [Virtualenv](https://virtualenv.pypa.io/en/latest/installation.html) (opcional)


### Comandos para instalação e execução

com docker e docker-compose:
```sh 
    docker-compose up -d
```
ou 

```sh 
    make start
```

com python:

passo exclusivo para uso com virtualenv
```sh 
    virtualenv .venv && source .venv/bin/activate
```

```sh 
    pip install -r requirements.txt
```

```sh 
    python manage.py runserver
```

### Guia de comandos do Makefile

- `make build` = Cria o build da imagem
- `make restart` =  Reinicia o container 
- `make stop` =  Para a aplicação
- `make test` =  Executa os testes da aplicação
- `make db` =  Acessa o banco de dados postgres
- `make migrations` =  Cria as migrations da aplicação
- `make migrate` =  Executa as migrations
- `make logs` =  Mostra os logs
- `make logs-api` =  Mostra somente os logs da API
- `make restart` =  Reinicia a aplicação
- `make seed` =  Popula o banco de dados
- `make superuser` =  Comando para criar um superusuario no sistema

