
package app.place_order_service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the app.place_order_service package. 
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

    private final static QName _OrderRequest_QNAME = new QName("place_order_service.app", "OrderRequest");
    private final static QName _OrderResponse_QNAME = new QName("place_order_service.app", "OrderResponse");
    private final static QName _OrderResponseArray_QNAME = new QName("place_order_service.app", "OrderResponseArray");
    private final static QName _OrderRequestArray_QNAME = new QName("place_order_service.app", "OrderRequestArray");
    private final static QName _OrderResponseOrderUniqueId_QNAME = new QName("place_order_service.app", "order_unique_id");
    private final static QName _OrderResponseStatus_QNAME = new QName("place_order_service.app", "status");
    private final static QName _OrderRequestAdditionalDetail_QNAME = new QName("place_order_service.app", "additional_detail");
    private final static QName _OrderRequestFromLng_QNAME = new QName("place_order_service.app", "from_lng");
    private final static QName _OrderRequestFromLat_QNAME = new QName("place_order_service.app", "from_lat");
    private final static QName _OrderRequestSenderSecretKey_QNAME = new QName("place_order_service.app", "sender_secret_key");
    private final static QName _OrderRequestDestination_QNAME = new QName("place_order_service.app", "destination");
    private final static QName _OrderRequestWeight_QNAME = new QName("place_order_service.app", "weight");
    private final static QName _OrderRequestReceiverName_QNAME = new QName("place_order_service.app", "receiver_name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: app.place_order_service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderRequest }
     * 
     */
    public OrderRequest createOrderRequest() {
        return new OrderRequest();
    }

    /**
     * Create an instance of {@link OrderResponseArray }
     * 
     */
    public OrderResponseArray createOrderResponseArray() {
        return new OrderResponseArray();
    }

    /**
     * Create an instance of {@link OrderRequestArray }
     * 
     */
    public OrderRequestArray createOrderRequestArray() {
        return new OrderRequestArray();
    }

    /**
     * Create an instance of {@link OrderResponse }
     * 
     */
    public OrderResponse createOrderResponse() {
        return new OrderResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "OrderRequest")
    public JAXBElement<OrderRequest> createOrderRequest(OrderRequest value) {
        return new JAXBElement<OrderRequest>(_OrderRequest_QNAME, OrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "OrderResponse")
    public JAXBElement<OrderResponse> createOrderResponse(OrderResponse value) {
        return new JAXBElement<OrderResponse>(_OrderResponse_QNAME, OrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderResponseArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "OrderResponseArray")
    public JAXBElement<OrderResponseArray> createOrderResponseArray(OrderResponseArray value) {
        return new JAXBElement<OrderResponseArray>(_OrderResponseArray_QNAME, OrderResponseArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderRequestArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "OrderRequestArray")
    public JAXBElement<OrderRequestArray> createOrderRequestArray(OrderRequestArray value) {
        return new JAXBElement<OrderRequestArray>(_OrderRequestArray_QNAME, OrderRequestArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "order_unique_id", scope = OrderResponse.class)
    public JAXBElement<String> createOrderResponseOrderUniqueId(String value) {
        return new JAXBElement<String>(_OrderResponseOrderUniqueId_QNAME, String.class, OrderResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "status", scope = OrderResponse.class)
    public JAXBElement<String> createOrderResponseStatus(String value) {
        return new JAXBElement<String>(_OrderResponseStatus_QNAME, String.class, OrderResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "additional_detail", scope = OrderRequest.class)
    public JAXBElement<String> createOrderRequestAdditionalDetail(String value) {
        return new JAXBElement<String>(_OrderRequestAdditionalDetail_QNAME, String.class, OrderRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "from_lng", scope = OrderRequest.class)
    public JAXBElement<Double> createOrderRequestFromLng(Double value) {
        return new JAXBElement<Double>(_OrderRequestFromLng_QNAME, Double.class, OrderRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "from_lat", scope = OrderRequest.class)
    public JAXBElement<Double> createOrderRequestFromLat(Double value) {
        return new JAXBElement<Double>(_OrderRequestFromLat_QNAME, Double.class, OrderRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "sender_secret_key", scope = OrderRequest.class)
    public JAXBElement<String> createOrderRequestSenderSecretKey(String value) {
        return new JAXBElement<String>(_OrderRequestSenderSecretKey_QNAME, String.class, OrderRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "destination", scope = OrderRequest.class)
    public JAXBElement<String> createOrderRequestDestination(String value) {
        return new JAXBElement<String>(_OrderRequestDestination_QNAME, String.class, OrderRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "weight", scope = OrderRequest.class)
    public JAXBElement<Double> createOrderRequestWeight(Double value) {
        return new JAXBElement<Double>(_OrderRequestWeight_QNAME, Double.class, OrderRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "place_order_service.app", name = "receiver_name", scope = OrderRequest.class)
    public JAXBElement<String> createOrderRequestReceiverName(String value) {
        return new JAXBElement<String>(_OrderRequestReceiverName_QNAME, String.class, OrderRequest.class, value);
    }

}
