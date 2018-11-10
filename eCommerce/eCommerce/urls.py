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

	#cr: /order-status/
	url(r'^order-status/$', searchOrderStatus),

	#cr: /response-types/
	url(r'^response-types/$', searchProductResponseTypes),
	
	###################################################################################################################
	#
	# User
	#
	###################################################################################################################

	#ct: /users/
	url(r'^users/$', UsersView.as_view()),
	#ct: /users/user1/wishlist/
	url(r'^users/(?P<username>.+)/wishlist/$', UserWishlist.as_view()),
	#ct: /users/user1/wishlist/1/
	url(r'^users/(?P<username>.+)/wishlist/(?P<product_id>[0-9]+)/$', UserWishlist.as_view()),
	#ct: /users/user1/cart/
	url(r'^users/(?P<username>.+)/cart/$', UserCart.as_view()),
	#ct: /users/user1/cart/1/
	url(r'^users/(?P<username>.+)/cart/(?P<product_id>[0-9]+)/$', UserCart.as_view()),
	#ct: /users/user1/products/
	url(r'^users/(?P<username>.+)/products/$', UserProduct.as_view()),
	#ct: /users/user1/orders/
	url(r'^users/(?P<username>.+)/orders/$', BuyerOrder.as_view()),
	#ct: /users/user1/
	url(r'^users/(?P<username>.+)/$', UserView.as_view()),

	###################################################################################################################
	#
	# Product
	#
	###################################################################################################################

	#ct: /products/
	url(r'^products/$', ProductsView.as_view()),
	#ct: /products/1/
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

	###################################################################################################################
	#
	# Order
	#
	###################################################################################################################

	#ct: /orders/
	url(r'^orders/$',OrdersView.as_view()),
	#ct: /orders/1/
	url(r'^orders/(?P<order_id>[0-9]+)/$',OrderView.as_view()),
	
	###################################################################################################################
	#
	# Product Response
	#
	###################################################################################################################

	#ct: /responses/
	url(r'^responses/$', ProductResponsesView.as_view()),
	#ct: /responses/1/
	url(r'^responses/(?P<response_id>[0-9]+)/$',ProductResponseView.as_view()),

	###################################################################################################################
	#
	# Promotion
	#
	###################################################################################################################

	#ct: /promotions/
	url(r'^promotions/$', PromotionsView.as_view()),
	#ct: /promotions/1/
	url(r'^promotions/(?P<promotion_id>[0-9]+)/$',PromotionView.as_view()),

]
