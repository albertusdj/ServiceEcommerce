from eCommerceApp.models import *
import django_filters

class OrderStatusFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    name = django_filters.CharFilter(lookup_expr='icontains')
    description = django_filters.CharFilter(lookup_expr='icontains')
    class Meta:
        model = OrderStatus
        fields = ['id', 'pk', 'name', 'description', ]

class ProductResponseTypeFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    name = django_filters.CharFilter(lookup_expr='icontains')
    class Meta:
        model = ProductResponseType
        fields = ['id', 'pk', 'name', ]
        
class UserFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    username = django_filters.CharFilter(lookup_expr='icontains')
    name = django_filters.CharFilter(lookup_expr='icontains')
    address = django_filters.CharFilter(lookup_expr='icontains')
    class Meta:
        model = User
        fields = ['id', 'pk', 'username', 'name', 'address', ]

class ProductFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    user = user_id = django_filters.NumberFilter(field_name='user_id')
    username = django_filters.CharFilter(lookup_expr='icontains', field_name='user__username')
    name = django_filters.CharFilter(lookup_expr='icontains')
    description = django_filters.CharFilter(lookup_expr='icontains')
    price = django_filters.NumberFilter()
    max_price = django_filters.NumberFilter(field_name='price', lookup_expr='lte')
    min_price = django_filters.NumberFilter(field_name='price', lookup_expr='gte')
    quantity = django_filters.NumberFilter()
    max_quantity = django_filters.NumberFilter(field_name='quantity', lookup_expr='lte')
    min_quantity = django_filters.NumberFilter(field_name='quantity', lookup_expr='gte')
    
    class Meta:
        model = Product
        fields = ['id', 'pk', 'user', 'user_id', 'username', 'name', 'description', 'price', 'min_price', 'max_price', 'quantity', 'min_quantity', 'max_quantity']

class WishlistFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    user = user_id = django_filters.NumberFilter(field_name='user_id')
    username = django_filters.CharFilter(lookup_expr='icontains', field_name='user__username')
    product = product_id = django_filters.NumberFilter(field_name='product_id')
    product_name = django_filters.CharFilter(lookup_expr='icontains', field_name='product__name')

    class Meta:
        model = Wishlist
        fields = ['id', 'pk', 'user', 'user_id', 'username', 'product', 'product_id', 'product_name', ]

class CartFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    username = django_filters.CharFilter(lookup_expr='icontains', field_name='user__username')
    user = user_id = django_filters.NumberFilter(field_name='user_id')
    product_name = django_filters.CharFilter(lookup_expr='icontains', field_name='product__name')
    product = product_id = django_filters.NumberFilter(field_name='product_id')
    quantity = django_filters.NumberFilter()
    max_quantity = django_filters.NumberFilter(field_name='quantity', lookup_expr='lte')
    min_quantity = django_filters.NumberFilter(field_name='quantity', lookup_expr='gte')
    subtotal = django_filters.NumberFilter()
    max_subtotal = django_filters.NumberFilter(field_name='subtotal', lookup_expr='lte')
    min_subtotal = django_filters.NumberFilter(field_name='subtotal', lookup_expr='gte')

    class Meta:
        model = Cart
        fields = ['id', 'pk', 'user', 'user_id', 'username', 'product', 'product_id', 'product_name', 'quantity', 'max_quantity', 'min_quantity', 'subtotal', 'min_subtotal', 'max_subtotal', ]

class OrderFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    user = user_id = django_filters.NumberFilter(field_name='user_id')
    username = django_filters.CharFilter(lookup_expr='icontains', field_name='user__username')
    product = product_id = django_filters.NumberFilter(field_name='orderdetail__product_id')
    product_name = django_filters.CharFilter(lookup_expr='icontains', field_name='orderdetail__product__name')
    total = django_filters.NumberFilter()
    max_total = django_filters.NumberFilter(field_name='total', lookup_expr='lte')
    min_total = django_filters.NumberFilter(field_name='total', lookup_expr='gte')
    promotion = promotion_id = django_filters.NumberFilter(field_name='orderdetail__promotion_id')
    promotion_name = django_filters.CharFilter(lookup_expr='icontains', field_name='orderdetail__promotion__name')
    
    class Meta:
        model = Order
        fields = ['id', 'pk', 'user', 'user_id', 'username', 'product', 'product_id', 'product_name', 'total', 'max_total', 'min_total', 'promotion', 'promotion_id', 'promotion_name', ]

class PromotionFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    product = product_id = django_filters.NumberFilter(field_name='product_id')
    product_name = django_filters.CharFilter(lookup_expr='icontains', field_name='product__name')
    name = django_filters.CharFilter(lookup_expr='icontains')
    description = django_filters.CharFilter(lookup_expr='icontains')
    is_valid = django_filters.BooleanFilter(field_name='is_valid', widget=django_filters.widgets.BooleanWidget())
    total = django_filters.NumberFilter()
    max_discount = django_filters.NumberFilter(field_name='discount', lookup_expr='lte')
    min_discount = django_filters.NumberFilter(field_name='discount', lookup_expr='gte')
    discount_percentage = django_filters.NumberFilter()
    max_discount_percentage = django_filters.NumberFilter(field_name='discount_percentage', lookup_expr='lte')
    min_discount_percentage = django_filters.NumberFilter(field_name='discount_percentage', lookup_expr='gte')

    class Meta:
        model = Promotion
        fields = ['id', 'pk', 'product', 'product_id', 'product_name', 'name', 'description', 'is_valid', 'discount', 'max_discount', 
        'min_discount', 'discount_percentage', 'max_discount_percentage', 'min_discount_percentage',]

class ProductResponseFilter(django_filters.FilterSet):
    pk = django_filters.NumberFilter(field_name='id')
    product = product_id = django_filters.NumberFilter(field_name='product_id')
    product_name = django_filters.CharFilter(lookup_expr='icontains', field_name='product__name')
    order = order_id = django_filters.NumberFilter(field_name='order_id')
    content = django_filters.CharFilter(lookup_expr='icontains')
    response_type = response_type_id = django_filters.NumberFilter(field_name='response_type_id')  
    response_type_name = django_filters.CharFilter(lookup_expr='icontains', field_name='response_type__name')

    class Meta:
        model = Promotion
        fields = ['id', 'pk', 'product', 'product_id', 'product_name', 'order', 'order_id', 'content', 'response_type', 'response_type_id', 'response_type_name']
