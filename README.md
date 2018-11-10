# ENVIRONMENT
1. `git clone http://gitlab.informatika.org/albertusdj/ServiceEcommerce`
2. `cd ServiceEcommerce`
3. `conda create environment -f yml`
4. `activate ppls`. bash/sudo: `source activate ppls`
5. `nano eCommerce/eCommerce/settings.py`  
`INSTALLED_APPS = [`  
&nbsp;&nbsp;&nbsp;&nbsp;`...`  
&nbsp;&nbsp;&nbsp;&nbsp;`'eCommerceApp',`  
&nbsp;&nbsp;&nbsp;&nbsp;`    `  
&nbsp;&nbsp;&nbsp;`]`  
`...`  
`DATABASES = {`  
&nbsp;&nbsp;&nbsp;&nbsp;`'default': {`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`'ENGINE': 'django.db.backends.sqlite3',`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),`  
&nbsp;&nbsp;&nbsp;&nbsp;`}`  
`}`  

6. `python manage.py makemigrations`
7. `python manage.py migrate`
8. `python manage.py runserver`
9. `source deactivate`

# MODELS
## User
	username = models.CharField(max_length=100, unique=True)
	name = models.CharField(max_length=100, default="")
	password = models.CharField(max_length=100)
	address = models.CharField(max_length=100, default="")

## Product
	user = models.ForeignKey(User, on_delete=models.CASCADE) 
	name = models.CharField(max_length=100) description = models.CharField(max_length=100, default="")
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

## Order
	order = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	quantity = models.IntegerField()
	total = models.FloatField(default=0)
	status = models.CharField(max_length=100)

## ProductResponse
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	order = models.ForeignKey(Order, on_delete=models.CASCADE, null=True)
	content = models.CharField(max_length=500, default="")

## Promotion
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	name = content = models.CharField(max_length=100)
	content = models.CharField(max_length=1000, default="")

# ENDPOINTS (notice trailing slash)
## User
###### INDEX: GET /users/ :heavy_check_mark:
###### CREATE: POST /users/ :heavy_check_mark:
###### SHOW: GET /users/{username}/ :heavy_check_mark:
###### UPDATE: PUT /users/{username}/ :heavy_check_mark:
###### DELETE: DELETE /users/{username}/ :heavy_check_mark:

###### GET WISHLIST OF USER: GET /users/{username}/wishlist/:heavy_check_mark:
###### ADD TO USER WISHLIST: POST /users/{username}/wishlist/
###### REMOVE FROM USER WISHLIST: DELETE /users/{username}/wishlist/{product-id}/

###### GET CART OF USER: GET /users/{username}/cart/ :heavy_check_mark:
###### ADD TO USER CART: POST /users/{username}/cart/
###### MODIFY QTY IN USER CART: PUT /users/{username}/cart/{product-id}/
###### REMOVE FROM USER CART: DELETE /users/{username}/cart/{product-id}/

###### GET ALL PRODUCTS SOLD BY USER: GET /users/{username}/products/
###### DELETE ALL PRODUCTS SOLD BY USER: DELETE /users/{username}/products/

###### GET ALL ORDERS OF BUYER: GET /users/{username}/orders/
###### DELETE ALL ORDERS OF BUYER: DELETE /users/{username}/orders/
###### GET ALL ORDERS OF SELLER: GET /users/{username}/sales/orders/
###### DELETE ALL ORDERS OF SELLER: DELETE /users/{username}/sales/orders/

###### GET RESPONSES OF BUYER: GET /users/{username}/responses/
###### DELETE ALL RESPONSES OF BUYER: /users/{username}/responses/
###### GET RESPONSES OF BUYER: GET /users/{username}/responses/
###### DELETE ALL RESPONSES OF BUYER: /users/{username}/responses/

## Product
###### INDEX: GET /products/
###### CREATE: POST /products/
###### SHOW: GET /products/{product-id}/
###### UPDATE: PUT /products/{product-id}/
###### DELETE: DELETE /products/{product-id}/

###### GET RESPONSES OF PRODUCT: GET /products/{product-id}/responses/
###### DELETE ALL RESPONSES OF PRODUCT: DELETE /products/{product-id}/responses/

###### GET PROMO OF PRODUCT: GET /products/{product-id}/promotions/
###### DELETE ALL PROMO OF PRODUCT: DELETE /products/{product-id}/promotions/

## Wishlist
###### INDEX: GET /wishlists/ :heavy_check_mark:

## Cart
###### INDEX: GET /carts/ :heavy_check_mark:

## Order
###### INDEX: GET /orders/
###### CREATE: POST /orders/
###### SHOW: GET /orders/{order-id}/
###### UPDATE: PUT /orders/{order-id}/
###### DELETE: DELETE /orders/{order-id}/

## ProductResponse
###### INDEX: GET /responses/
###### CREATE: POST /responses/
###### SHOW: GET /responses/{response-id}/
###### UPDATE: PUT /responses/{response-id}/
###### DELETE: DELETE /responses/{response-id}/

## Promotion
###### INDEX: GET /promotions/
###### CREATE: POST /promotions/
###### SHOW: GET /promotions/{promotion-id}/
###### UPDATE: PUT /promotions/{promotion-id}/
###### DELETE: DELETE /promotions/{promotion-id}/

