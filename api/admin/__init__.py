from django.contrib import admin
from api.models.usuario import Usuario
from .usuario import UsuarioAdmin

admin.site.register(Usuario, UsuarioAdmin)

