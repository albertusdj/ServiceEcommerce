# ENVIRONMENT
1. `git clone http://gitlab.informatika.org/albertusdj/ServiceEcommerce`
2. `cd ServiceEcommerce`
3. `conda env create -f environment.yml`
4.`activate ppls`. bash/sudo: `source activate ppls`
5. `nano eCommerce/eCommerce/settings.py`
`INSTALLED_APPS = [
    ...
    'eCommerceApp',

]
...
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
    }
}`

6. `python manage.py makemigrations`
7. `python manage.py migrate`
8. `python manage.py runserver`
9. to access sqlite db `python manage.py dbshell` 
10. `source deactivate`



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

###### GET ALL ORDERS OF BUYER: GET /users/{username}/orders/ :heavy_check_mark:
###### DELETE ALL ORDERS OF BUYER: DELETE /users/{username}/orders/ :heavy_check_mark:
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
###### INDEX: GET /orders/ :heavy_check_mark:
###### CREATE: POST /orders/ :heavy_check_mark:
###### SHOW: GET /orders/{order-id}/ :heavy_check_mark:
###### UPDATE: PUT /orders/{order-id}/ :heavy_check_mark:
###### DELETE: DELETE /orders/{order-id}/ :heavy_check_mark:

## ProductResponse
###### INDEX: GET /responses/ :heavy_check_mark:
###### CREATE: POST /responses/ :heavy_check_mark:
###### SHOW: GET /responses/{response-id}/ :heavy_check_mark:
###### UPDATE: PUT /responses/{response-id}/ :heavy_check_mark:
###### DELETE: DELETE /responses/{response-id}/ :heavy_check_mark:

## Promotion
###### INDEX: GET /promotions/ :heavy_check_mark:
###### CREATE: POST /promotions/ :heavy_check_mark:
###### SHOW: GET /promotions/{promotion-id}/ :heavy_check_mark:
###### UPDATE: PUT /promotions/{promotion-id}/ :heavy_check_mark:
###### DELETE: DELETE /promotions/{promotion-id}/ :heavy_check_mark:

