from django.urls import include, path, re_path
from api.views.token import MyTokenObtainPairView, MyTokenRefreshView
from rest_framework_simplejwt.views import TokenRefreshView
from .usuario import usuario_router 

urlpatterns = [

    path('', include(usuario_router.urls)),
    path('login/', MyTokenObtainPairView.as_view(), name='login'),
    path('refresh/', MyTokenRefreshView.as_view(), name='refresh'),

]
