from api.serializers.usuarioSerializer import UsuarioReadSerializer, UsuarioWriteSerializer
from api.models.usuario import Usuario
from rest_framework import viewsets
from rest_framework.permissions import IsAuthenticated
from api.permissions.todos import TodosPodemCriar, TodosPodemDeletar, TodosPodemEditar, TodosPodemVer
from drf_spectacular.utils import extend_schema, OpenApiResponse
from ..filters.usuario import UsuarioFilter
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.filters import  SearchFilter


class UsuarioViewSet(viewsets.ModelViewSet):
    filter_backends = [DjangoFilterBackend, SearchFilter]
    queryset = Usuario.objects.all()
    search_fields = ['nome', 'telefone']
    filterset_fields = ['ativo']
    permission_classes = [IsAuthenticated, (TodosPodemDeletar | TodosPodemEditar | TodosPodemVer | TodosPodemCriar)]
    serializer_class = UsuarioReadSerializer
    http_method_names = ['get', 'post', 'put', 'patch', 'delete']
    filter_class = UsuarioFilter
    @extend_schema(
        request=UsuarioReadSerializer,
        responses={
            200: UsuarioReadSerializer,
            204: OpenApiResponse(description="Sem conteúdo"),
            400: OpenApiResponse(description="Requisição inválida"),
            403: OpenApiResponse(description="Proibido"),
            401: OpenApiResponse(description="Usuário não autorizado ou não autenticado"),
            500: OpenApiResponse(description="Erro no servidor"),
        },
        description='Este endpoint retorna os usuarios'
    )
    def get_serializer_class(self):
        if self.request.method == 'GET':
            return UsuarioReadSerializer
        return UsuarioWriteSerializer
    def perform_update(self, serializer):
        serializer.save(changed_by=self.request.user)

