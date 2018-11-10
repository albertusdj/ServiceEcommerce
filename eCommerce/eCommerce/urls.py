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

	#ct: /tes/
	#url(r'^tes/$', tes),

	###################################################################################################################
	#
	# User
	#
	###################################################################################################################

	#ct: /users/
	url(r'^users/$', UsersView.as_view()),
	#ct: /users/user1/wishlist/
	url(r'^users/(?P<username>.+)/wishlist/$', UserWishlist.as_view()),
	#ct: /users/user1/cart/
	url(r'^users/(?P<username>.+)/cart/$', UserCart.as_view()),
	#ct: /users/user1/
	url(r'^users/(?P<username>.+)/$', UserView.as_view()),

	###################################################################################################################
	#
	# Product
	#
	###################################################################################################################
	#ct: /products/
	url(r'^products/$', ProductsView.as_view()),
	#ct: /products/product1/
	url(r'^products/(?P<product_id>[0-9]+)/$', ProductView.as_view()),

	###################################################################################################################
	#
	# Wishlist
	#
	###################################################################################################################
	#ct: /wishlists/
	url(r'^wishlists/$', WishlistsView.as_view()),

	###################################################################################################################
	#
	# Cart
	#
	###################################################################################################################

	#ct: /carts/
	url(r'^carts/$', CartsView.as_view()),

]
