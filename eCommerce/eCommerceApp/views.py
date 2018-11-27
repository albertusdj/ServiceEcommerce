from django.core import serializers
from django.db.models import Sum
from django.shortcuts import render, get_object_or_404
from django.http import Http404
from django.views.generic import View
from django.utils.decorators import method_decorator
from django.views.decorators.csrf import ensure_csrf_cookie, csrf_exempt
from django.http import HttpResponse, HttpResponseNotFound
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

class CSRFExemptMixin(object):
	@method_decorator(csrf_exempt)
	def dispatch(self, *args, **kwargs):
		return super(CSRFExemptMixin, self).dispatch(*args, **kwargs)

def validate_search(request, filtered):
	fields = filtered.Meta.fields
	for key,value in request.GET.items():
		if key not in fields:
			raise Http404("Key "+key+" does not exist on model "+filtered.Meta.model.__name__+". Available keys are " + ",".join(fields))
		return

def searchOrderStatus(request):
	status = OrderStatus.objects.all()
	filtered = OrderStatusFilter(request.GET, queryset=status)
	validate_search(request, filtered)
	response = serializers.serialize('json', filtered.qs)
	return HttpResponse(response)

def searchProductResponseTypes(request):
	response_types = ProductResponseType.objects.all()
	filtered = ProductResponseTypeFilter(request.GET, queryset=response_types)
	validate_search(request, filtered)
	response = serializers.serialize('json', filtered.qs)
	return HttpResponse(response)

###################################################################################################################
#
# User
#
###################################################################################################################

class UsersView(CSRFExemptMixin, View):
	# Users Index
	def get(self, request):
		users = User.objects.all() #queryset
		filtered = UserFilter(request.GET, queryset=users)
		validate_search(request, filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Create user
	def post(self, request):
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)

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

class UserView(CSRFExemptMixin, View):
	# Show user
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		response = serializers.serialize('json', [user]) #for object
		return HttpResponse(response)

	# Update user
	def put(self, request, username):
		user = get_object_or_404(User, username=username)
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)

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
class ProductsView(CSRFExemptMixin, View):
	# Index
	def get(self, request):
		products = Product.objects.all() #queryset
		filtered = ProductFilter(request.GET, queryset=products)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Create
	def post(self, request):
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)

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

class ProductView(CSRFExemptMixin, View):
	# Show
	def get(self, request, product_id):
		product = get_object_or_404(Product, id=product_id) #object
		response = serializers.serialize('json', [product]) #for object
		return HttpResponse(response)

	# Update
	def put(self, request, product_id):
		product = get_object_or_404(Product, id=product_id)
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)

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

class UserProduct(CSRFExemptMixin, View):
	# Show All
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		products = Product.objects.filter(user=user)
		filtered = ProductFilter(request.GET,queryset=products)
		response = serializers.serialize('json', filtered.qs) #for object
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
class WishlistsView(CSRFExemptMixin, View):
	# Wishlists Index
	def get(self, request):
		wishlists = Wishlist.objects.all() #queryset
		filtered = WishlistFilter(request.GET,queryset=wishlists)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

class UserWishlist(CSRFExemptMixin, View):
	# User wishlist
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		wishlists = Wishlist.objects.filter(user=user)
		filtered = WishlistFilter(request.GET,queryset=wishlists)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs) #for object
		return HttpResponse(response)

	# Add to wishlist
	def post(self, request, username):
		user = get_object_or_404(User, username=username) #object
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)
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
class CartsView(CSRFExemptMixin, View):
	# Carts Index
	def get(self, request):
		carts = Cart.objects.all() #queryset
		filtered = CartFilter(request.GET,queryset=carts)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

class UserCart(CSRFExemptMixin, View):
	# User cart
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		carts = Cart.objects.filter(user=user)
		filtered = CartFilter(request.GET,queryset=carts)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Add to cart
	def post(self, request, username):
		user = get_object_or_404(User, username=username) #object
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)
		product_id = body['product_id']
		product = get_object_or_404(Product, pk=product_id)
		cart = Cart.objects.get_or_create(user=user,product=product)
		try:
			cart[0].quantity+=body['quantity']
		except:
			cart[0].quantity=1
		cart[0].subtotal = product.price * cart[0].quantity
		cart[0].save()

		response = serializers.serialize('json', [cart[0]])
		return HttpResponse(response)

	# Modify in cart
	def put(self, request, username, product_id):
		user = get_object_or_404(User, username=username) #object
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)
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
class OrdersView(CSRFExemptMixin, View):
	# Orders Index
	def get(self, request):
		orders = Order.objects.all()
		filtered = OrderFilter(request.GET, queryset=orders)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Create Order
	def post(self,request):
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)
		username = body['username']
		user = get_object_or_404(User, username=username)
		data={}
		data['user'] = user
		try:
			status_id = body['status_id']
			data['status'] = get_object_or_404(OrderStatus,pk=status_id)
		except:
			data['status'] =  OrderStatus.objects.filter(name__icontains="pending").first()
		order = Order(**data)
		order.save()

		orderDetails = body['details']
		for orderDetail in orderDetails:
			data_d={}
			data_d['order'] = order
			product_id = orderDetail['id'];
			product = get_object_or_404(Product, pk=product_id)
			data_d['product'] = product
			quantity = orderDetail['quantity']
			data_d['quantity'] = quantity
			subtotal = product.price * quantity
			try:
				promotion_id = orderDetail['promotion_id'];
			except:
				promotion_id = None
			if (promotion_id):
				promotion = get_object_or_404(Promotion, pk=promotion_id, product_id=product_id)
				data_d['promotion'] = promotion
				if (promotion.is_valid):
					if (promotion.discount!=0):
						subtotal -= promotion.discount
					else :
						subtotal -= subtotal * promotion.discount_percentage		
			d = OrderDetail(**data_d)
			d.save()
			order.total+= subtotal
		order.save()
		response = serializers.serialize('json', [order])
		return HttpResponse(response)

class OrderView(CSRFExemptMixin, View):
	# Show Specific Order Detail
	def get(self, request, order_id):
		order = get_object_or_404(Order, pk=order_id)
		response = serializers.serialize('json', [order])
		return HttpResponse(response)

	# Update Specific Order Detail
	def put(self,request,order_id):
		order = get_object_or_404(Order, pk=order_id)
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)
		try:
			status_id = body['status_id']
			order.status = get_object_or_404(OrderStatus,pk=status_id)
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

class OrderDetailsView(CSRFExemptMixin, View):
	# Order Products Index
	def get(self, request, order_id):
		order = get_object_or_404(Order, pk=order_id)
		orderDetails = get_object_or_404(Order, pk=order_id)
		filtered = ProductFilter(request.GET, queryset=orderDetails)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

class BuyerOrder(CSRFExemptMixin, View):
	# Get All Order of Buyer
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		orders = Order.objects.filter(user=user).distinct()
		filtered = OrderFilter(request.GET, queryset=orders)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Delete All order of Buyer
	def delete(self, request, username):
		user = get_object_or_404(User, username=username) #object
		order = Order.objects.filter(user=user)
		order.delete()
		return HttpResponse(status=204)

class SellerOrder(CSRFExemptMixin, View):
	# Get All Order of Seller
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		orders = Order.objects.filter(products__user=user).distinct()
		filtered = OrderFilter(request.GET, queryset=orders)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Delete All order of Seller
	def delete(self, request, username):
		user = get_object_or_404(User, username=username) #object
		order = Order.objects.filter(products__user=user)
		order.delete()
		return HttpResponse(status=204)

class BuyerResponse(CSRFExemptMixin, View):
	# Get All response of Buyer
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		presponses = ProductResponse.objects.filter(user=user)
		filtered = ProductResponseFilter(request.GET,queryset=presponses)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Delete All response of Buyer
	def delete(self, request, username):
		user = get_object_or_404(User, username=username) #object
		presponses = ProductResponse.objects.filter(user=user)
		presponses.delete()
		return HttpResponse(status=204)

class SellerResponse(CSRFExemptMixin, View):
	# Get All response of Seller
	def get(self, request, username):
		user = get_object_or_404(User, username=username) #object
		presponses = ProductResponse.objects.filter(product__user=user)
		filtered = ProductResponseFilter(request.GET,queryset=presponses)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Delete All response of Seller
	def delete(self, request, username):
		user = get_object_or_404(User, username=username) #object
		presponses = ProductResponse.objects.filter(product__user=user)
		presponses.delete()
		return HttpResponse(status=204)

###################################################################################################################
#
# ProductResponse
#
###################################################################################################################

class ProductResponsesView(CSRFExemptMixin, View):
	# Product Responses Index
	def get(self, request):
		product_responses = ProductResponse.objects.all()
		filtered = ProductResponseFilter(request.GET,queryset=product_responses)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Create Product Response
	def post(self,request):
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)
		username = body['username']
		user = get_object_or_404(User, username=username)
		data = {}
		data['user'] = user
		product_id = body['product_id']
		data['product'] = get_object_or_404(Product, pk=product_id)
		response_type_id = body['response_type_id']
		response_type = get_object_or_404(ProductResponseType,id=response_type_id)
		data['response_type'] = response_type
		if (response_type_id in [1,2]) : #rating or review
			order_id = body['order_id']
			data['order'] = get_object_or_404(Order,pk=order_id)
		data['content'] = body['content']
		product_response = ProductResponse(**data)
		product_response.save()
		response = serializers.serialize('json', [product_response])
		return HttpResponse(response)

class ProductResponseView(CSRFExemptMixin, View):
	# Show Specific Product Response
	def get(self, request, response_id):
		product_response = get_object_or_404(ProductResponse, pk=response_id)
		response = serializers.serialize('json', [product_response])
		return HttpResponse(response)

	# Update Specific Product Response
	def put(self, request, response_id):
		product_response = get_object_or_404(ProductResponse, pk=response_id)
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)
		promotion.content = body['content']
		product_response.save()
		response = serializers.serialize('json', [product_response])
		return HttpResponse(response)

	# Delete Specific Product Response
	def delete(self,request,response_id):
		product_response = get_object_or_404(ProductResponse, pk=response_id)
		product_response.delete()
		return HttpResponse(status=204)

class ProductProductResponse(CSRFExemptMixin, View):
	# Show All
	def get(self, request, product_id):
		product = get_object_or_404(Product, pk=product_id) #object
		product_responses = ProductResponse.objects.filter(product=product)
		filtered = ProductResponseFilter(request.GET,queryset=product_responses)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Delete All
	def delete(self, request, product_id):
		product = get_object_or_404(Product, pk=product_id) #object
		product_responses = ProductResponse.objects.filter(product=product)
		product_responses.delete()
		return HttpResponse(status=204)

###################################################################################################################
#
# Promotion
#
###################################################################################################################

class PromotionsView(CSRFExemptMixin, View):
	# Promotions Index
	def get(self, request):
		promotions = Promotion.objects.all()
		filtered = PromotionFilter(request.GET, queryset=promotions)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Create Promotion
	def post(self,request):
		query_string = request.body.decode('utf-8')
		body = json.loads(query_string)

		product_id = body['product_id']
		product = get_object_or_404(Product, id=product_id)
		data = {}
		data['product'] = product
		data['name'] = body['name']
		try:
			data['description'] = body['description']
		except:
			pass
		try:
			data['discount'] = body['discount']
		except:
			data['discount_percentage'] = body['discount_percentage']
		try:
			data['is_valid'] = body['is_valid']
		except:
			pass
		promotion = Promotion(**data)
		promotion.save()
		response = serializers.serialize('json', [promotion])
		return HttpResponse(response)

class PromotionView(CSRFExemptMixin, View):
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
		body = json.loads(query_string)
		try:
			promotion.name = body['name']
		except:
			pass
		try:
			promotion.is_valid = body['is_valid']
		except:
			pass
		try:
			product_response.description = body['description']
		except:
			pass
		try:
			product_response.discount = body['discount']
			product_response.discount_percentage = 0
		except:
			product_response.discount= 0
			product_response.discount_percentage = body['discount_percentage']
		promotion.save()
		response = serializers.serialize('json', [promotion])
		return HttpResponse(response)

	# Delete Specific Promotion
	def delete(self,request,promotion_id):
		promotion = get_object_or_404(Promotion, pk=promotion_id)
		promotion.delete()
		return HttpResponse(status=204)

class ProductPromotion(CSRFExemptMixin, View):
	# Show All
	def get(self, request, product_id):
		product = get_object_or_404(Product, pk=product_id) #object
		promotions = Promotion.objects.filter(product=product)
		filtered = PromotionFilter(request.GET, queryset=promotions)
		validate_search(request,filtered)
		response = serializers.serialize('json', filtered.qs)
		return HttpResponse(response)

	# Delete All
	def delete(self, request, product_id):
		product = get_object_or_404(Product, pk=product_id) #object
		promotions = Promotion.objects.filter(product=product)
		promotions.delete()
		return HttpResponse(status=204)
