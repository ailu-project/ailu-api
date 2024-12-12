from django.db import models
import uuid
from django.contrib.auth.models import User
from django.utils import timezone
from simple_history.models import HistoricalRecords


def data_expiracao_futura():
    data = timezone.now().date()
    try:
        return data.replace(year=data.year + 2)
    except ValueError:
        data = data.replace(year=data.year + 2, month=2, day=28)
    return data


def definir_idade(data_nascimento):
    if data_nascimento:
        return (timezone.now().date() - data_nascimento).days // 365
    return None

class Usuario(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    nome = models.CharField(max_length=255)
    cpf = models.CharField(max_length=11, unique=True, null=False, blank=False)
    data_nascimento = models.DateField(null=True, blank=True)
    idade = models.IntegerField(null=True, blank=True)
    telefone = models.CharField(max_length=20, null=True, blank=True)
    ativo = models.BooleanField(default=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    data_expiracao = models.DateField(default=data_expiracao_futura)
    changed_by = models.ForeignKey(
        User, on_delete=models.SET_NULL, null=True,
        blank=True, related_name='usuario_changed_by')

    class Meta:
        verbose_name = "Usuario"
        verbose_name_plural = "Usuarios"

    def save(self, *args, **kwargs):
        if self.data_nascimento:
            self.idade = definir_idade(self.data_nascimento)
        super().save(*args, **kwargs)

    @property
    def history_user(self):
        return self.changed_by
    
    @history_user.setter
    def history_user(self, user):
        self.changed_by = user

    def __str__(self):
        return self.nome







