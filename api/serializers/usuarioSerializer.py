from rest_framework import serializers
from api.models.usuario import Usuario
from .UserSerializer import UserSerializer
from validate_docbr import CPF

from drf_spectacular.utils import extend_schema_serializer, OpenApiExample

@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Exemplo de resposta de Usuario',
            summary='Exemplo de resposta',
            description='Este é um exemplo de uma resposta ao consultar os dados de um Usuario.',
            value={
                'user': {
                    'username': 'user123',
                    'email': 'user@example.com',
                    'first_name': 'João',
                    'last_name': 'Silva'
                },
                'cpf': '522.778.674-70',
                'nome': 'João Silva',
                'telefone': '(84) 99999-9999',
                'ativo': True,
                'data_expiracao': '2025-12-31',
                'idade': 32,
                'changed_by': 1
            },
            response_only=True,
        ),
    ]
)
class UsuarioReadSerializer(serializers.ModelSerializer):
    """
    Serializer de leitura de Usuario
    """
    user = UserSerializer()

    class Meta:
        model = Usuario
        fields = ('user', 'cpf', 'nome', 'telefone', 'ativo', 'data_expiracao', 'idade', 'changed_by', 'created_at', 'updated_at')
        read_only_fields = ['id', 'created_at', 'updated_at']


def partial_update(instance, validated_data):
    """
    Atualiza um Usuario
    """
    instance.user = validated_data.get('user', instance.user)
    instance.cpf = validated_data.get('cpf', instance.cpf)
    instance.nome = validated_data.get('nome', instance.nome)
    instance.telefone = validated_data.get('telefone', instance.telefone)
    instance.ativo = validated_data.get('ativo', instance.ativo)
    instance.changed_by = validated_data.get('changed_by', instance.changed_by)
    instance.save()
    return instance

@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Exemplo de request do Usuario 1',
            summary='Exemplo de request do Usuario 1',
            description='Este é um exemplo de entrada para criar um novo Usuario.',
            value={
                'user': {
                    'username': 'joao_silva',
                    'email': 'joao@example.com',
                    'first_name': 'João',
                    'last_name': 'Silva'
                },
                'cpf': '095.804.344-25',
                'nome': 'João Silva',
                'telefone': '8499999-1111',
                'changed_by': 1
            },
            request_only=True,
        ),
    ]
)
class UsuarioWriteSerializer(serializers.ModelSerializer):
    """
    Serializer de escrita de Usuario
    """
    user = UserSerializer()

    class Meta:
        model = Usuario
        fields = ('user', 'cpf', 'nome', 'telefone', 'changed_by')

    @staticmethod
    def validate_cpf(value):
        """
        Valida o CPF
        """
        cpf = CPF()
        if not cpf.validate(value):
            raise serializers.ValidationError('CPF inválido')
        return value

    def create(self, validated_data):
        """
        Cria um Usuario
        """
        required_fields = ['cpf', 'nome', 'telefone']
        for field in required_fields:
            if field not in validated_data:
                raise serializers.ValidationError({field: 'Este campo é obrigatório'})
        user_data = validated_data.pop('user')
        user = UserSerializer.create(UserSerializer(), validated_data=user_data)
        usuario = Usuario.objects.create(user=user, **validated_data)
        return usuario
