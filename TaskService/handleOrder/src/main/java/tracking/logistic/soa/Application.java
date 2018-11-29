
package tracking.logistic.soa;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import app.tracking_service.TrackingRequestArray;
import app.tracking_service.TrackingResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Application", targetNamespace = "soa.logistic.tracking")
@XmlSeeAlso({
    app.tracking_service.ObjectFactory.class,
    tracking.logistic.soa.ObjectFactory.class
})
public interface Application {


    /**
     * 
     * @param trackingRequest
     * @return
     *     returns app.tracking_service.TrackingResponse
     */
    @WebMethod(operationName = "get_tracking", action = "get_tracking")
    @WebResult(name = "get_trackingResult", targetNamespace = "soa.logistic.tracking")
    @RequestWrapper(localName = "get_tracking", targetNamespace = "soa.logistic.tracking", className = "tracking.logistic.soa.GetTracking")
    @ResponseWrapper(localName = "get_trackingResponse", targetNamespace = "soa.logistic.tracking", className = "tracking.logistic.soa.GetTrackingResponse")
    public TrackingResponse getTracking(
        @WebParam(name = "tracking_request", targetNamespace = "soa.logistic.tracking")
        TrackingRequestArray trackingRequest);

}
