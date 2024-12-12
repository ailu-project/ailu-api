import factory
from django.contrib.auth.models import User

class UserFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = User

    username = factory.Faker('user_name', locale='pt_BR')
    email = factory.Faker('email', locale='pt_BR')
    first_name = factory.Faker('first_name',locale='pt_BR')
    last_name = factory.Faker('last_name',locale='pt_BR')
    password = factory.Faker('password')
