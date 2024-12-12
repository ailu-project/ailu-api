from django.core.exceptions import ObjectDoesNotExist
from rest_framework import exceptions
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer, TokenRefreshSerializer
from datetime import datetime
from api.models.usuario import Usuario


def valida_data_expiracao(data_expiracao):
    data_atual = datetime.now().date()
    if data_atual > data_expiracao:
        raise exceptions.AuthenticationFailed(detail="Data de expiração ultrapassada")


def busca_data_expiracao(user):
    if user.is_superuser:
        return None
    try:
        usuario = Usuario.objects.get(user=user)
        return usuario.data_expiracao
    except ObjectDoesNotExist:
        return None


def is_usuario_ativo(user):
    return Usuario.objects.filter(user=user, ativo=True).exists()


class MyTokenObtainPairSerializer(TokenObtainPairSerializer):
    @classmethod
    def get_token(cls, user):
        token = super().get_token(user)
        token['username'] = user.username
        token['email'] = user.email
        token['date_joined'] = str(user.date_joined)
        token['is_usuario_ativo'] = is_usuario_ativo(user)

        data_expiracao_usuario = busca_data_expiracao(user)
        token['data_expiracao_sethas'] = data_expiracao_usuario.isoformat() if data_expiracao_usuario else None

        return token
    
class MyTokenRefreshSerializer(TokenRefreshSerializer):
    def validate(self, attrs):
        data = super().validate(attrs)
        usuario = self.user
        refresh = self.get_token(usuario)
        if not usuario.is_superuser:
            data_expiracao_usuario = busca_data_expiracao(usuario)
            valida_data_expiracao(data_expiracao_usuario)
