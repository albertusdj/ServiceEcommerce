
package app.tracking_service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the app.tracking_service package. 
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

    private final static QName _TrackingRequest_QNAME = new QName("tracking_service.app", "TrackingRequest");
    private final static QName _TrackingResponse_QNAME = new QName("tracking_service.app", "TrackingResponse");
    private final static QName _TrackingRequestArray_QNAME = new QName("tracking_service.app", "TrackingRequestArray");
    private final static QName _TrackingResponseAdditionalDetail_QNAME = new QName("tracking_service.app", "additional_detail");
    private final static QName _TrackingResponseStatus_QNAME = new QName("tracking_service.app", "status");
    private final static QName _TrackingResponseCurrentLocation_QNAME = new QName("tracking_service.app", "current_location");
    private final static QName _TrackingRequestSecretKey_QNAME = new QName("tracking_service.app", "secret_key");
    private final static QName _TrackingRequestOrderUniqueId_QNAME = new QName("tracking_service.app", "order_unique_id");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: app.tracking_service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TrackingRequest }
     * 
     */
    public TrackingRequest createTrackingRequest() {
        return new TrackingRequest();
    }

    /**
     * Create an instance of {@link TrackingResponse }
     * 
     */
    public TrackingResponse createTrackingResponse() {
        return new TrackingResponse();
    }

    /**
     * Create an instance of {@link TrackingRequestArray }
     * 
     */
    public TrackingRequestArray createTrackingRequestArray() {
        return new TrackingRequestArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrackingRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "TrackingRequest")
    public JAXBElement<TrackingRequest> createTrackingRequest(TrackingRequest value) {
        return new JAXBElement<TrackingRequest>(_TrackingRequest_QNAME, TrackingRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrackingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "TrackingResponse")
    public JAXBElement<TrackingResponse> createTrackingResponse(TrackingResponse value) {
        return new JAXBElement<TrackingResponse>(_TrackingResponse_QNAME, TrackingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrackingRequestArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "TrackingRequestArray")
    public JAXBElement<TrackingRequestArray> createTrackingRequestArray(TrackingRequestArray value) {
        return new JAXBElement<TrackingRequestArray>(_TrackingRequestArray_QNAME, TrackingRequestArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "additional_detail", scope = TrackingResponse.class)
    public JAXBElement<String> createTrackingResponseAdditionalDetail(String value) {
        return new JAXBElement<String>(_TrackingResponseAdditionalDetail_QNAME, String.class, TrackingResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "status", scope = TrackingResponse.class)
    public JAXBElement<String> createTrackingResponseStatus(String value) {
        return new JAXBElement<String>(_TrackingResponseStatus_QNAME, String.class, TrackingResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "current_location", scope = TrackingResponse.class)
    public JAXBElement<String> createTrackingResponseCurrentLocation(String value) {
        return new JAXBElement<String>(_TrackingResponseCurrentLocation_QNAME, String.class, TrackingResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "secret_key", scope = TrackingRequest.class)
    public JAXBElement<String> createTrackingRequestSecretKey(String value) {
        return new JAXBElement<String>(_TrackingRequestSecretKey_QNAME, String.class, TrackingRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tracking_service.app", name = "order_unique_id", scope = TrackingRequest.class)
    public JAXBElement<String> createTrackingRequestOrderUniqueId(String value) {
        return new JAXBElement<String>(_TrackingRequestOrderUniqueId_QNAME, String.class, TrackingRequest.class, value);
    }

}
