from django.contrib import admin
from django.urls import path, re_path, include
from drf_spectacular.views import SpectacularAPIView, SpectacularRedocView, SpectacularSwaggerView
from rest_framework import permissions
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/test/download', SpectacularAPIView.as_view(), name='schema'),
    path('api/test/', SpectacularSwaggerView.as_view(url_name='schema'), name='swagger-ui'),
    path('api/test/redoc/', SpectacularRedocView.as_view(url_name='schema'), name='redoc'),
    re_path('api/', include('api.urls')),
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)

if settings.DEBUG:
    urlpatterns.append(path('api/redoc/', SpectacularRedocView.as_view(url_name='schema'), name='redoc'))
