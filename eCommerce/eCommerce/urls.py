"""eCommerce URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.conf.urls import url
from eCommerceApp.views import *

urlpatterns = [
	# Karena pake wildcard, urutannya harus diperhatiin. regex yg lebih spesifik diatas!

	#url(r'^tes/$', views.tes),

	url(r'^users/$', UsersView.as_view()),
	url(r'^users/(?P<username>.+)/wishlist/$', WishlistView.as_view()),
	url(r'^users/(?P<username>.+)/cart/$', CartView.as_view()),
	url(r'^users/(?P<username>.+)/$', UserView.as_view()),

	url(r'^wishlists/$', WishlistsView.as_view()),
	url(r'^carts/$', CartsView.as_view()),

]
