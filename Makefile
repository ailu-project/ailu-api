DOCKER_COMPOSE_DEV := docker-compose -f docker-compose.yml
DOCKER_COMPOSE_PROD := docker-compose -f docker-compose.prod.yml
DJANGO := docker exec ailu-api-dev python manage.py
LOGS_CMD := docker logs
build-prod:
	@${DOCKER_COMPOSE_PROD} build

start-prod:
	@${DOCKER_COMPOSE_PROD} up -d

stop-prod:
	@${DOCKER_COMPOSE_PROD} down --remove-orphans

entrar_django:
	@${DOCKER_COMPOSE_DEV} exec api bash

entrar_django-prod:
	@${DOCKER_COMPOSE_PROD} exec api bash

ativar_lint:
	@${DOCKER_COMPOSE_DEV} exec api pylint --load-plugins pylint_django --django-settings-module=ailu_api.settings --disable=duplicate-code,unused-argument,too-many-ancestors,redefined-outer-name,redefined-builtin,line-too-long,too-many-locals,undefined-variable,cyclic-import,too-many-branches,arguments-differ,imported-auth-user  api/models/

restart-prod:
	@${DOCKER_COMPOSE_PROD} restart

build:
	@${DOCKER_COMPOSE_DEV} build

start:
	@${DOCKER_COMPOSE_DEV} up -d

stop:
	@${DOCKER_COMPOSE_DEV} down --remove-orphans

restart:
	@${DOCKER_COMPOSE_DEV} restart

migrations:
	@${DJANGO} makemigrations

migrate:
	@${DJANGO} migrate

migrations-alt:
	@${DJANGO} makemigrations api --empty

seed:
	@${DJANGO} seed

seed-clean:
	@${DJANGO} seed --clean
	
seed-prod:
	@${DJANGO} seed --prod

db:
	@docker-compose -f docker-compose.yml exec db psql -U user -d ailu

superuser:
	@${DJANGO} createsuperuser

db-dev:
	@docker-compose -f docker-compose.yml exec db-dev psql -U user -d ailu

criar_backup:
	@${DOCKER_COMPOSE_DEV} exec db-dev pg_dump -U user -d ailu > backup.sql

restaurar-backup:
	@docker cp backup.sql db-dev:/var/backup.sql
	@${DOCKER_COMPOSE_DEV} exec db psql -U user -d ailu -f  /var/backup.sql


create-views:
	@${DJANGO}  db_views

create-periodo-s:
	@${DJANGO} criar_periodos 2026 1 3 --semanal

logs-prod:
	@${DOCKER_COMPOSE_PROD} logs


logs:
	@${DOCKER_COMPOSE_DEV} logs

logs-api:
	@${LOGS_CMD} ailu-api-dev

logs-db:
	@${LOGS_CMD} ailu-db-dev

test:
	@${DJANGO} test

test-unit:
	@${DJANGO} test api.tests.models

test-int:
	@${DJANGO} test api.tests.views
	
collectstatic:
	@${DJANGO} collectstatic --no-input --clear
