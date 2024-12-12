import django_filters
from ..models.usuario import Usuario

class UsuarioFilter(django_filters.FilterSet):
    ativo = django_filters.BooleanFilter(field_name='ativo')
    nome = django_filters.CharFilter(field_name='nome', lookup_expr='icontains')
    telefone = django_filters.CharFilter(field_name='telefone', lookup_expr='icontains')

    class Meta:
        model = Usuario
        fields = ['nome','telefone', 'ativo']
