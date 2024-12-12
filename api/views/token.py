from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView
from api.serializers.token import MyTokenObtainPairSerializer, MyTokenRefreshSerializer

class MyTokenObtainPairView(TokenObtainPairView):
    serializer_class = MyTokenObtainPairSerializer

class MyTokenRefreshView(TokenRefreshView):
    serializer_class = MyTokenRefreshSerializer
