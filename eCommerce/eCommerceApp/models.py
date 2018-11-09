from django.db import models

class User(models.Model):
	username = models.CharField(max_length=100, unique=True)
	name = models.CharField(max_length=100, default="")
	password = models.CharField(max_length=100)
	address = models.CharField(max_length=100, default="")

class Product(models.Model):
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	name = models.CharField(max_length=100)
	description = models.CharField(max_length=100, default="")
	price = models.FloatField(default=0)
	quantity = models.IntegerField(default=0)

class Wishlist(models.Model):
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)

class Cart(models.Model):
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	quantity = models.IntegerField()
	subtotal = models.FloatField(default=0)

class Order(models.Model):
	order = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	quantity = models.IntegerField()
	total = models.FloatField(default=0)
	status = models.CharField(max_length=100)

class ProductResponse(models.Model):
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	order = models.ForeignKey(Order, on_delete=models.CASCADE, null=True)
	content = models.CharField(max_length=500, default="")

class Promotion(models.Model):
	product = models.ForeignKey(Product, on_delete=models.CASCADE)
	name = content = models.CharField(max_length=100)
	content = models.CharField(max_length=1000, default="")
