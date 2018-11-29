
package tracking.logistic.soa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import app.tracking_service.TrackingRequestArray;
import app.tracking_service.TrackingResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tracking.logistic.soa package. 
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

    private final static QName _GetTracking_QNAME = new QName("soa.logistic.tracking", "get_tracking");
    private final static QName _GetTrackingResponse_QNAME = new QName("soa.logistic.tracking", "get_trackingResponse");
    private final static QName _GetTrackingTrackingRequest_QNAME = new QName("soa.logistic.tracking", "tracking_request");
    private final static QName _GetTrackingResponseGetTrackingResult_QNAME = new QName("soa.logistic.tracking", "get_trackingResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tracking.logistic.soa
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTrackingResponse }
     * 
     */
    public GetTrackingResponse createGetTrackingResponse() {
        return new GetTrackingResponse();
    }

    /**
     * Create an instance of {@link GetTracking }
     * 
     */
    public GetTracking createGetTracking() {
        return new GetTracking();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTracking }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.tracking", name = "get_tracking")
    public JAXBElement<GetTracking> createGetTracking(GetTracking value) {
        return new JAXBElement<GetTracking>(_GetTracking_QNAME, GetTracking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTrackingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.tracking", name = "get_trackingResponse")
    public JAXBElement<GetTrackingResponse> createGetTrackingResponse(GetTrackingResponse value) {
        return new JAXBElement<GetTrackingResponse>(_GetTrackingResponse_QNAME, GetTrackingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrackingRequestArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.tracking", name = "tracking_request", scope = GetTracking.class)
    public JAXBElement<TrackingRequestArray> createGetTrackingTrackingRequest(TrackingRequestArray value) {
        return new JAXBElement<TrackingRequestArray>(_GetTrackingTrackingRequest_QNAME, TrackingRequestArray.class, GetTracking.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrackingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "soa.logistic.tracking", name = "get_trackingResult", scope = GetTrackingResponse.class)
    public JAXBElement<TrackingResponse> createGetTrackingResponseGetTrackingResult(TrackingResponse value) {
        return new JAXBElement<TrackingResponse>(_GetTrackingResponseGetTrackingResult_QNAME, TrackingResponse.class, GetTrackingResponse.class, value);
    }

}
