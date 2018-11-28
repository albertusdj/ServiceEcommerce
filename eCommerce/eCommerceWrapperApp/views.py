from django.views.generic import View
from django.shortcuts import render, get_object_or_404

from django.views.decorators.csrf import csrf_exempt
from spyne.application import Application
from spyne.decorator import rpc
from spyne.model.primitive import Unicode, Integer
from spyne.protocol.soap import Soap11
from spyne.server.django import DjangoApplication
from spyne.service import ServiceBase
import base64, requests

link = 'http://127.0.0.1:8080/engine-rest/message'
headers = {
    "Content-Type" : "application/json",
}

class Checkout(ServiceBase):
    @rpc(Unicode(nillable=False),Unicode(nillable=False), _returns=Integer)
    def sendCheckoutRequest(ctx, name, paymentmethod):
        data_checkout = {
            "username" : {
                "value" : name,
                "type" : "String"
            },
            "paymentMethod" : {
                "value" : paymentmethod,
                "type"  : "String"
            }
        }
        data = {
            "messageName" : "ProductCheckout",
            "businessKey" : 1,
            "processVariables" : data_checkout   
        }
        print (data)
        r = requests.post(link, json=data, headers=headers)
        return r.status_code
        
class HandleOrder(ServiceBase):
    @rpc(Integer(nillable=False), _returns=Integer)
    def orderHandlerID(ctx, order_id):
        data_order = {
            "orderID" : {
                "value" : order_id,
                "type" : "String"
            }
        }
        data = {
            "messageName" : "OrderReceived",
            "businessKey" : 2,
            "processVariables" : data_order
        }
        r = requests.post(link, json=data, headers=headers)
        return r.status_code
class Refund(ServiceBase):
    @rpc(Integer(nillable=False), _returns=Integer)
    def orderToRefund(ctx, order_id):
        data_order = {
            "orderID" : {
                "value" : order_id,
                "type" : "Integer",
            }
        }
        data = {
            "messageName" : "RefundRequest",
            "businessKey" : 3,
            "processVariable" : data_order
        }
        r = requests.post(link, json=data, headers=headers)
        return r.status_code

soap_app = Application(
    [Checkout, HandleOrder, Refund],
    tns='django.soap.example',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11(),
)

django_soap_application = DjangoApplication(soap_app)
my_soap_application = csrf_exempt(django_soap_application)