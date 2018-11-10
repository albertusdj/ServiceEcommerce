from django.core import serializers
from django.shortcuts import render, get_object_or_404
from django.views.generic import View
from django.views.decorators.csrf import ensure_csrf_cookie
from django.http import HttpResponse
from eCommerceApp.models import User, Product, Wishlist, Cart, Order, ProductResponse, Promotion

import json
from urllib import parse as urlparse

'''
@ensure_csrf_cookie
def tes(request):
	c = request.__dict__
	return render(request, 'tes.html', c)
'''

###################################################################################################################
#
# User
#
###################################################################################################################
class UsersView(View):
	# Users Index
	def get(self, request):
		users = User.objects.all() #queryset
		response = serializers.serialize('json', users) #u/ queryset
		return HttpResponse(response)

	# Create user
	def post(self, request):
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)

		# TO-DO : VALIDATION
		username = body['username']
		if (User.objects.filter(username=username).count()>0):
			return HttpResponse(status=403)
		data = {}
		data['username'] = body['username']
		data['password'] = body['password']
		try :
			data['name'] = body['name']
		except:
			pass
		try :
			data['address'] = body['address']
		except:
			pass
		user = User(**data)
		user.save()
		response = serializers.serialize('json', [user])
		return HttpResponse(response)

class UserView(View):
	# Show user
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		response = serializers.serialize('json', [user]) #for object
		return HttpResponse(response)

	# Update user
	def put(self, request, username):
		user = get_object_or_404(User, username=username)
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)

		# TO-DO : VALIDATION
		try:
			user.password = body['password']
		except:
			pass
		try:
			user.name = body['name']
		except:
			pass
		try:
			user.address = body['address']
		except:
			pass

		user.save()

		response = serializers.serialize('json', [user])
		return HttpResponse(response)

	# Delete user
	def delete(self, request, username):
		user = get_object_or_404(User, username=username)
		user.delete()
		return HttpResponse(status=204)

###################################################################################################################
#
# Product
#
###################################################################################################################
class ProductsView(View):
	# Index
	def get(self, request):
		products = Product.objects.all() #queryset
		response = serializers.serialize('json', products) #u/ queryset
		return HttpResponse(response)

	# Create
	def post(self, request):
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)

		# TO-DO : VALIDATION
		username = body['username']
		if (User.objects.filter(username=username).count()>0):
			return HttpResponse(status=403)
		data = {}
		data['username'] = body['username']
		try :
			data['name'] = body['name']
		except:
			pass
		try :
			data['price'] = body['price']
		except:
			pass
		try :
			data['quantity'] = body['quantity']
		except:
			pass
		product = Product(**data)
		product.save()
		response = serializers.serialize('json', [product])
		return HttpResponse(response)

class ProductView(View):
	# Show
	def get(self, request, product_id):
		product = get_object_or_404(Product, id=product_id) #object
		response = serializers.serialize('json', [product]) #for object
		return HttpResponse(response)

	# Update
	def put(self, request, product_id):
		product = get_object_or_404(Product, id=product_id)
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)

		# TO-DO : VALIDATION
		try:
			product.name = body['name']
		except:
			pass
		try:
			product.price = body['price']
		except:
			pass
		try:
			product.quantity = body['quantity']
		except:
			pass

		product.save()

		response = serializers.serialize('json', [product])
		return HttpResponse(response)

	# Delete
	def delete(self, request, product_id):
		product = get_object_or_404(Product, id=product_id)
		product.delete()
		return HttpResponse(status=204)

###################################################################################################################
#
# Wishlist
#
###################################################################################################################
class WishlistsView(View):
	# Wishlists Index
	def get(self, request):
		wishlists = Wishlist.objects.all() #queryset
		response = serializers.serialize('json', wishlists) #u/ queryset
		return HttpResponse(response)

class UserWishlist(View):
	# User wishlist
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		wishlist = Wishlist.objects.filter(user=user).first()
		response = "[]"
		if wishlist :
			response = serializers.serialize('json', [wishlist]) #for object
		return HttpResponse(response)

###################################################################################################################
#
# Cart
#
###################################################################################################################
class CartsView(View):
	# Carts Index
	def get(self, request):
		carts = Cart.objects.all() #queryset
		response = serializers.serialize('json', carts) #u/ queryset
		return HttpResponse(response)

class UserCart(View):
	# User cart
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		cart = Cart.objects.filter(user=user).first()
		response = "[]"
		if cart :
			response = serializers.serialize('json', [cart]) #for object
		return HttpResponse(response)

###################################################################################################################
#
# Order
#
###################################################################################################################

###################################################################################################################
#
# ProductResponse
#
###################################################################################################################

###################################################################################################################
#
# Promotion
#
###################################################################################################################
