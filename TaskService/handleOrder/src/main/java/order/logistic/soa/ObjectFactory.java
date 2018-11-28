
package order.logistic.soa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import app.place_order_service.OrderRequestArray;
import app.place_order_service.OrderResponseArray;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the order.logistic.soa package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PlaceOrderResponse_QNAME = new QName("soa.logistic.order", "place_orderResponse");
    private final static QName _PlaceOrder_QNAME = new QName("soa.logistic.order", "place_order");
    private final static QName _PlaceOrderResponsePlaceOrderResult_QNAME = new QName("soa.logistic.order", "place_orderResult");
    private final static QName _PlaceOrderOrderRequests_QNAME = new QName("soa.logistic.order", "order_requests");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: order.logistic.soa
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PlaceOrderResponse }
     * 
     */
    public PlaceOrderResponse createPlaceOrderResponse() {
        return new PlaceOrderResponse();
    }

    /**
     * Create an instance of {@link PlaceOrder }
     * 
     */
    public PlaceOrder createPlaceOrder() {
        return new PlaceOrder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaceOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.order", name = "place_orderResponse")
    public JAXBElement<PlaceOrderResponse> createPlaceOrderResponse(PlaceOrderResponse value) {
        return new JAXBElement<PlaceOrderResponse>(_PlaceOrderResponse_QNAME, PlaceOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaceOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.order", name = "place_order")
    public JAXBElement<PlaceOrder> createPlaceOrder(PlaceOrder value) {
        return new JAXBElement<PlaceOrder>(_PlaceOrder_QNAME, PlaceOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderResponseArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.order", name = "place_orderResult", scope = PlaceOrderResponse.class)
    public JAXBElement<OrderResponseArray> createPlaceOrderResponsePlaceOrderResult(OrderResponseArray value) {
        return new JAXBElement<OrderResponseArray>(_PlaceOrderResponsePlaceOrderResult_QNAME, OrderResponseArray.class, PlaceOrderResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderRequestArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.order", name = "order_requests", scope = PlaceOrder.class)
    public JAXBElement<OrderRequestArray> createPlaceOrderOrderRequests(OrderRequestArray value) {
        return new JAXBElement<OrderRequestArray>(_PlaceOrderOrderRequests_QNAME, OrderRequestArray.class, PlaceOrder.class, value);
    }

}
