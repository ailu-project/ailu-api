import factory
from .user import UserFactory
from api.models.usuario import Usuario


class UsuarioFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = Usuario

    user = factory.SubFactory(UserFactory)
    nome = factory.Faker('name', locale='pt_BR') 
    cpf = factory.Faker('cpf', locale='pt_BR')
    data_nascimento = factory.Faker('date_of_birth', locale='pt_BR')
    telefone = factory.Faker('phone_number', locale='pt_BR')
    ativo = factory.Faker('boolean', chance_of_getting_true=80)  
    created_at = factory.Faker('date_time_this_year') 
    updated_at = factory.Faker('date_time_this_month')
    data_expiracao = factory.Faker('future_date')
    changed_by = factory.SubFactory(UserFactory) 