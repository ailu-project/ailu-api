from django.contrib import admin
from django.utils.translation import gettext_lazy as _
class UpdateOrderFilter(admin.SimpleListFilter):
    title = _('Ordenar por data de atualização')
    parameter_name = 'updated_at'
    
    def lookups(self, request, model_admin):
        return [
            ('asc', _('Mais antigo primeiro')),
            ('desc', _('Mais recente primeiro')),
        ]
    def queryset(self, request, queryset):
        if self.value() == 'asc':
            return queryset.order_by('updated_at')  
        elif self.value() == 'desc':
            return queryset.order_by('-updated_at')
        return queryset
class UsuarioAdmin(admin.ModelAdmin):
    search_fields = ['nome', 'user__email', 'telefone']
    list_filter = ['ativo',UpdateOrderFilter]
    list_display = ['nome', 'updated_at']

