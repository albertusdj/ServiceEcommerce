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
    user = django_filters.NumberFilter(field_name='user_id')
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
        fields = ['id', 'pk', 'user', 'username', 'name', 'description', 'price', 'min_price', 'max_price', 'quantity', 'min_quantity', 'max_quantity']
