# ENVIRONMENT
###### 1. `git clone http://gitlab.informatika.org/albertusdj/ServiceEcommerce`
###### 2. `cd ServiceEcommerce`
###### 3. `conda create environment -f yml`
###### 4. `activate ppls`. bash/sudo: `source activate ppls`
###### 5. `nano eCommerce/eCommerce/settings.py`
	INSTALLED_APPS = [  
		...  
		'eCommerceApp',
		
	]  
	...  
	DATABASES = {  
		'default': {  
			'ENGINE': 'django.db.backends.sqlite3',
			'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
		}
	} 

###### 6. `python manage.py makemigrations`
###### 7. `python manage.py migrate`
###### 8. `python manage.py loaddata eCommerce.json`
###### 9. `python manage.py runserver`
###### 10. to access sqlite db `python manage.py dbshell`
###### 111. `source deactivate`

# MODELS
## User
	username = models.CharField(max_length=100, unique=True)
	name = models.CharField(max_length=100, default="")
	password = models.CharField(max_length=100)
	address = models.CharField(max_length=100, default="")

## Product
	user = models.ForeignKey(User, on_delete=models.CASCADE) 
	name = models.CharField(max_length=100) description = models.CharField(max_length=100, default="")
	description = models.CharField(max_length=100, default="")
	price = models.FloatField(default=0)
	quantity = models.IntegerField(default=0)

## Wishlist
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)

## Cart
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	quantity = models.IntegerField()
	subtotal = models.FloatField(default=0)

## Order Status
	name = models.CharField(max_length=100)
	description = models.CharField(max_length=500)

## Order
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	products = models.ManyToManyField(Product, through='OrderProduct')
	total = models.FloatField(default=0)
	status = models.ForeignKey(OrderStatus, on_delete=models.CASCADE)

## OrderProduct
	order = models.ForeignKey(Order, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	quantity = models.IntegerField()

## Product Response Type
	name = models.CharField(max_length=100)

## Product Response
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	order = models.ForeignKey(Order, on_delete=models.CASCADE, null=True)
	response_type = models.ForeignKey(ProductResponseType, on_delete=models.CASCADE)
	content = models.CharField(max_length=500)

## Promotion
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	name = content = models.CharField(max_length=100)
	content = models.CharField(max_length=1000, default="")
	is_valid = models.BooleanField(default=True)

# ENDPOINTS (notice trailing slash)
## User
###### INDEX: GET /users/ :heavy_check_mark:
###### CREATE: POST /users/ :heavy_check_mark:
	{
		   "username":"admin",
		   "password":"password"
	}
###### SHOW: GET /users/{username}/ :heavy_check_mark:
###### UPDATE: PUT /users/{username}/ :heavy_check_mark:
	{
		   "name":"admin admin",
		   "address":"Indonesia"
	}
###### DELETE: DELETE /users/{username}/ :heavy_check_mark:

###### GET WISHLIST OF USER: GET /users/{username}/wishlist/ :heavy_check_mark:
###### ADD TO USER WISHLIST: POST /users/{username}/wishlist/ :heavy_check_mark:
	{
		   "product_id":1
	}
###### REMOVE FROM USER WISHLIST: DELETE /users/{username}/wishlist/{product-id}/ :heavy_check_mark:

###### GET CART OF USER: GET /users/{username}/cart/ :heavy_check_mark:
###### ADD TO USER CART: POST /users/{username}/cart/ :heavy_check_mark:
	{
		   "product_id":1,
		   "quantity":9
	}
	quantity opsional, default qty = 1. kalo di body ada qty jd di-sum sama yg ada sekarang
###### MODIFY QTY IN USER CART: PUT /users/{username}/cart/{product-id}/ :heavy_check_mark:
	{
		   "product_id":1,
		   "quantity":1
	}
###### REMOVE FROM USER CART: DELETE /users/{username}/cart/{product-id}/ :heavy_check_mark:

###### GET ALL PRODUCTS SOLD BY USER: GET /users/{username}/products/ :heavy_check_mark:
###### DELETE ALL PRODUCTS SOLD BY USER: DELETE /users/{username}/products/ :heavy_check_mark:

###### GET ALL ORDERS OF BUYER: GET /users/{username}/orders/ :heavy_check_mark:
###### DELETE ALL ORDERS OF BUYER: DELETE /users/{username}/orders/ :heavy_check_mark:
###### GET ALL ORDERS OF SELLER: GET /users/{username}/sales/orders/ :heavy_check_mark:
###### DELETE ALL ORDERS OF SELLER: DELETE /users/{username}/sales/orders/ :heavy_check_mark:

###### GET RESPONSES OF BUYER: GET /users/{username}/responses/ :heavy_check_mark:
###### DELETE ALL RESPONSES OF BUYER: /users/{username}/responses/ :heavy_check_mark:
###### GET RESPONSES OF SELLER: GET /users/{username}/sales/responses/ :heavy_check_mark:
###### DELETE ALL RESPONSES OF SELLER: /users/{username}/sales/responses/ :heavy_check_mark:

## Product
###### INDEX: GET /products/ :heavy_check_mark:
###### CREATE: POST /products/ :heavy_check_mark:
	{
		   "username":"admin",
		   "name": "barang bekas"
	}
	default value silakan dilihat sendiri
###### SHOW: GET /products/{product-id}/ :heavy_check_mark:
###### UPDATE: PUT /products/{product-id}/ :heavy_check_mark:
	{
		   "price":"9999999",
		   "quantity": "2"
	}
###### DELETE: DELETE /products/{product-id}/ :heavy_check_mark:

###### GET RESPONSES OF PRODUCT: GET /products/{product-id}/responses/ :heavy_check_mark:
###### DELETE ALL RESPONSES OF PRODUCT: DELETE /products/{product-id}/responses/ :heavy_check_mark:

###### GET PROMO OF PRODUCT: GET /products/{product-id}/promotions/ :heavy_check_mark:
###### DELETE ALL PROMO OF PRODUCT: DELETE /products/{product-id}/promotions/ :heavy_check_mark:

## Wishlist
###### INDEX: GET /wishlists/ :heavy_check_mark:

## Cart
###### INDEX: GET /carts/ :heavy_check_mark:

## Order
###### INDEX: GET /orders/ :heavy_check_mark:
###### CREATE: POST /orders/ :heavy_check_mark:
	{
		   "username":"admin",
		   "products_id": [1,2],
		   "quantities": [9,10],
		   "status_id":2
	}
	status opsional, status default = "pending", total diitung otomatis
###### SHOW: GET /orders/{order-id}/ :heavy_check_mark:
###### UPDATE: PUT /orders/{order-id}/ :heavy_check_mark:
	{
		   "status_id":3,
		   "total":20
	}
	yg bisa diganti cmn status ma total(biar promotion bs ngurangin harga)
###### DELETE: DELETE /orders/{order-id}/ :heavy_check_mark:

## ProductResponse
###### INDEX: GET /responses/ :heavy_check_mark:
###### CREATE: POST /responses/ :heavy_check_mark:
	{
		"username":"user",
		"product_id":4,
		"response_type_id":1,
		"order_id":9,
		"content":"5"
	}
	order_id opsional, tapi rating + review harus ada order_id
###### SHOW: GET /responses/{response-id}/ :heavy_check_mark:
###### UPDATE: PUT /responses/{response-id}/ :heavy_check_mark:
	{
		"content":"eh salah"
	}
	yg bisa diganti cuma contentnya
###### DELETE: DELETE /responses/{response-id}/ :heavy_check_mark:

## Promotion
###### INDEX: GET /promotions/ :heavy_check_mark:
###### CREATE: POST /promotions/ :heavy_check_mark:
	{
		"product_id":1,
		"name":"diskonkaos"
	}
	default value silakan dilihat sendiri
###### SHOW: GET /promotions/{promotion-id}/ :heavy_check_mark:
###### UPDATE: PUT /promotions/{promotion-id}/ :heavy_check_mark:
	{
		"content":"no description",
		"is_valid":0
	}
	bisi mau ngeganti content, is_valid bs diganti biar ga valid lagi
###### DELETE: DELETE /promotions/{promotion-id}/ :heavy_check_mark:

