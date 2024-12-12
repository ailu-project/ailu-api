from django.core.management.base import BaseCommand
from api.seed.utils.factories.usuario import UsuarioFactory
from api.models.usuario import Usuario

class Command(BaseCommand):
    help = 'Seed com dados de Usuarios'

    def add_arguments(self, parser):
        parser.add_argument(
            '--clean',
            action='store_true',
            help='Limpa os dados existentes antes de criar as seeds',
        )
    
    def handle(self, *args, **kwargs):
        if kwargs['clean']:
            Usuario.objects.all().delete()
            self.stdout.write(self.style.SUCCESS('Dados de Usuarios limpos com sucesso'))
        for _ in range(10):
            usuario = UsuarioFactory.create()
            self.stdout.write(self.style.SUCCESS(f'Usuario: {usuario.nome} criado com sucesso'))
