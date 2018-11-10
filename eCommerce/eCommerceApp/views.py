from django.core import serializers
from django.shortcuts import render, get_object_or_404
from django.views.generic import View
from django.views.decorators.csrf import ensure_csrf_cookie
from django.http import HttpResponse
from eCommerceApp.models import User, Product, Wishlist, Cart, OrderStatus, Order, ProductResponseType, ProductResponse, Promotion
from eCommerceApp.filters import *

import json
from urllib import parse as urlparse

'''
@ensure_csrf_cookie
def tes(request):
	c = request.__dict__
	return render(request, 'tes.html', c)
'''

def searchOrderStatus(request):
	status = OrderStatus.objects.all()
	filtered = OrderStatusFilter(request.GET, queryset=status)
	response = serializers.serialize('json', filtered.qs)
	return HttpResponse(response)
	
def searchProductResponseTypes(request):
	response_types = ProductResponseType.objects.all()
	filtered = ProductResponseTypeFilter(request.GET, queryset=response_types)
	response = serializers.serialize('json', filtered.qs)
	return HttpResponse(response)

###################################################################################################################
#
# User
#
###################################################################################################################
class UsersView(View):
	# Users Index
	def get(self, request):
		users = User.objects.all() #queryset
		filtered = UserFilter(request.GET, queryset=users)
		response = serializers.serialize('json', filtered.qs)
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
		filtered = ProductFilter(request.GET, queryset=products)
		response = serializers.serialize('json', filtered.qs)
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
		user = get_object_or_404(User, username=username)
		data = {}
		data['user'] = user
		try :
			data['name'] = body['name']
		except:
			pass
		try :
			data['description'] = body['description']
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
			product.description = body['description']
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

class UserProduct(View):
	# Show All
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		products = Product.objects.filter(user=user)
		response = serializers.serialize('json', products) #for object
		return HttpResponse(response)
		
	# Delete All
	def delete(self, request, username):
		user = get_object_or_404(User, username=username) #object
		products = Product.objects.filter(user=user)
		products.delete()
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
		wishlist = Wishlist.objects.filter(user=user)
		response = serializers.serialize('json', wishlist) #for object
		return HttpResponse(response)

	# Add to wishlist
	def post(self, request, username):
		user = get_object_or_404(User, username=username) #object
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)
		product_id = body['product_id']
		product = get_object_or_404(Product, pk=product_id)
		wishlist = Wishlist.objects.get_or_create(user=user,product=product)
		response = serializers.serialize('json', [wishlist[0]])
		return HttpResponse(response)

	# Remove from wishlist
	def delete(self, request, username, product_id):
		user = get_object_or_404(User, username=username)
		product = get_object_or_404(Product, pk=product_id)
		wishlist = Wishlist.objects.filter(user=user,product=product)
		wishlist.delete()
		return HttpResponse(status=204)

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
		cart = Cart.objects.filter(user=user)
		response = serializers.serialize('json', cart) #for object
		return HttpResponse(response)

	# Add to cart
	def post(self, request, username):
		user = get_object_or_404(User, username=username) #object
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)
		product_id = body['product_id']
		product = get_object_or_404(Product, pk=product_id)
		cart = Cart.objects.get_or_create(user=user,product=product)
		try:
			cart[0].quantity+=body['quantity']
		except:
			pass
		cart[0].subtotal = product.price * cart[0].quantity
		cart[0].save()

		response = serializers.serialize('json', [cart[0]])
		return HttpResponse(response)

	# Modify in cart
	def put(self, request, username, product_id):
		user = get_object_or_404(User, username=username) #object
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)
		product_id = body['product_id']
		product = get_object_or_404(Product, pk=product_id)
		cart = get_object_or_404(Cart, user=user, product=product)
		cart.quantity=body['quantity']
		if (cart.quantity == 0) :
			return self.delete(request, username, product_id)
		cart.subtotal = product.price * cart.quantity
		cart.save()
		response = serializers.serialize('json', [cart])
		return HttpResponse(response)

	# Remove from cart
	def delete(self, request, username, product_id):
		user = get_object_or_404(User, username=username)
		product = get_object_or_404(Product, pk=product_id)
		cart = Cart.objects.filter(user=user, product=product)
		cart.delete()
		return HttpResponse(status=204)

###################################################################################################################
#
# Order
#
###################################################################################################################
class OrdersView(View):
	# Orders Index
	def get(self, request):
		orders = Order.objects.all()
		response = serializers.serialize('json', orders)
		return HttpResponse(response)
	# Create Order
	def post(self,request):
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)
		# Belum Di handle kalau jumlah produk array
		products = body['product']
		if (products.objects.filter(pk=products).count() == 0):
			return HttpResponse(status=403)
		data = {}
		data['products'] = body['products']
		try:
			data['quantity'] = body['quantity']
		except:
			pass
		try:
			data['total'] = body['total']
		except:
			pass
		try:
			data['status'] = body['status']
		except:
			pass
		order = Order(**data)
		order.save()
		response = serializers.serialize('json', [order])
		return HttpResponse(response)

class OrderView(View):
	# Show Specific Order Detail
	def get(self, request, order_id):
		order = get_object_or_404(Order, pk=order_id)
		if order :
			response = serializers.serialize('json', [order])
		return HttpResponse(response)

	# Update Specific Order Detail
	def put(self,request,order_id):
		order = get_object_or_404(Order, pk=order_id)
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)
		try:
			order.quantity = body['quantity']
		except:
			pass
		try:
			user.total = body['total']
		except:
			pass
		try:
			user.status = body['status']
		except:
			pass
		order.save()
		response = serializers.serialize('json', [order])
		return HttpResponse(response)

	# Delete Specific Order
	def delete(self,request,order_id):
		order = get_object_or_404(Order, pk=order_id)
		order.delete()
		return HttpResponse(status=204)

class BuyerOrder(View):
	# Get All Order of Buyer
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		order = order.objects.filter(user=user).first()
		response = "[]"
		if wishlist :
			response = serializers.serialize('json', [order]) #for object
		return HttpResponse(response)
	# Delete All order of Buyer
	def delete(self, request, username):
		user = get_object_or_404(User, username=username) #object
		order = order.objects.filter(user=user).first()
		order.delete()
		return HttpResponse(status=204)

# class SellerOrder(View):
	# Get All Order of Seller
	# def get(self, request, username):

	# Delete All order of Seller
	# def delete(self, request, username):

###################################################################################################################
#
# ProductResponse
#
###################################################################################################################
class ProductResponsesView(View):
	# Product Responses Index
	def get(self, request):
		product_responses = ProductResponse.objects.all()
		response = serializers.serialize('json', product_responses)
		return HttpResponse(response)
	# Create Product Response
	def post(self,request):
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)

		username = body['username']
		user = get_object_or_404(User, username=username)
		product_id = body['product_id']
		order_id = body['order_id']
		data = {}
		data['user_id'] = user.id
		data['product_id'] = product_id
		data['order_id'] = order_id
		try:
			data['content'] = body['content']
		except:
			pass
		product_response = ProductResponse(**data)
		product_response.save()
		response = serializers.serialize('json', [product_response])
		return HttpResponse(response)
class ProductResponseView(View):
	# Show Specific Product Response
	def get(self, request, response_id):
		product_response = get_object_or_404(ProductResponse, pk=response_id)
		if product_response :
			response = serializers.serialize('json', [product_response])
		return HttpResponse(response)

	# Update Specific Product Response
	def put(self, request, order_id):
		product_response = get_object_or_404(Order, pk=response_id)
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)
		try:
			product_response.content = body['content']
		except:
			pass
		product_response.save()
		response = serializers.serialize('json', [product_response])
		return HttpResponse(response)

	# Delete Specific Product Response
	def delete(self,request,response_id):
		product_response = get_object_or_404(Order, pk=response_id)
		product_response.delete()
		return HttpResponse(status=204)
###################################################################################################################
#
# Promotion
#
###################################################################################################################
class PromotionsView(View):
	# Promotions Index
	def get(self, request):
		promotions = Promotion.objects.all()
		response = serializers.serialize('json', promotions)
		return HttpResponse(response)
	# Create Promotion
	def post(self,request):
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)

		product_id = body['product_id']
		product = get_object_or_404(Product, id=product_id)
		data = {}
		data['product'] = product
		try:
			data['name'] = body['name']
		except:
			pass
		try:
			data['content'] = body['content']
		except:
			pass
		try:
			data['is_valid'] = body['is_valid']
		except:
			pass
		promotion = Promotion(**data)
		promotion.save()
		response = serializers.serialize('json', [promotion])
		return HttpResponse(response)
class PromotionView(View):
	# Show Specific Promotion
	def get(self, request, promotion_id):
		promotion = get_object_or_404(Promotion, id=promotion_id)
		if promotion :
			response = serializers.serialize('json', [promotion])
		return HttpResponse(response)

	# Update Specific Promotion
	def put(self, request, promotion_id):
		promotion = get_object_or_404(Promotion, pk=promotion_id)
		query_string = request.body.decode('utf-8')
		try:
			# u/ text, text/plain, application/json
			body = json.loads(query_string)
		except Exception as e:
			# u/ application/x-www-urlencoded
			# pass
			json_string = json.dumps(urlparse.parse_qs(query_string))
			body = json.loads(json_string)
		try:
			promotion.content = body['content']
		except:
			pass
		try:
			promotion.name = body['name']
		except:
			pass
		try:
			promotion.is_valid = body['is_valid']
		except:
			pass
		promotion.save()
		response = serializers.serialize('json', [promotion])
		return HttpResponse(response)

	# Delete Specific Promotion
	def delete(self,request,promotion_id):
		promotion = get_object_or_404(Promotion, pk=promotion_id)
		promotion.delete()
		return HttpResponse(status=204)
