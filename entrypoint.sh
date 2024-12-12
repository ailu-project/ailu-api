#!/bin/bash

# Aguardar a inicialização do PostgreSQL
if [ "$DATABASE" = "postgres" ]; then
    echo "Aguardando o PostgreSQL iniciar..."

    # Verificar se o banco está acessível na rede
    while ! nc -z $SQL_HOST $SQL_PORT; do
        echo "Esperando o PostgreSQL... ($SQL_HOST:$SQL_PORT)"
        sleep 0.1
    done

    echo "PostgreSQL iniciado."
fi

# Executar as migrações do Django
echo "Executando migrações..."
python3 manage.py makemigrations
python3 manage.py migrate

# Executar o comando fornecido (ex: python manage.py runserver)
echo "Iniciando o servidor..."
exec "$@"
