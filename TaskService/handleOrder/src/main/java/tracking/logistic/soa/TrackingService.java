
package tracking.logistic.soa;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TrackingService", targetNamespace = "soa.logistic.tracking", wsdlLocation = "file:/C:/Users/albert/workspace/handleOrder/src/main/java/com/eCommerce/service/handleOrder/tracking.xml")
public class TrackingService
    extends Service
{

    private final static URL TRACKINGSERVICE_WSDL_LOCATION;
    private final static WebServiceException TRACKINGSERVICE_EXCEPTION;
    private final static QName TRACKINGSERVICE_QNAME = new QName("soa.logistic.tracking", "TrackingService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/albert/workspace/handleOrder/src/main/java/com/eCommerce/service/handleOrder/tracking.xml");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRACKINGSERVICE_WSDL_LOCATION = url;
        TRACKINGSERVICE_EXCEPTION = e;
    }

    public TrackingService() {
        super(__getWsdlLocation(), TRACKINGSERVICE_QNAME);
    }

    public TrackingService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRACKINGSERVICE_QNAME, features);
    }

    public TrackingService(URL wsdlLocation) {
        super(wsdlLocation, TRACKINGSERVICE_QNAME);
    }

    public TrackingService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRACKINGSERVICE_QNAME, features);
    }

    public TrackingService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TrackingService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Application
     */
    @WebEndpoint(name = "Application")
    public Application getApplication() {
        return super.getPort(new QName("soa.logistic.tracking", "Application"), Application.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Application
     */
    @WebEndpoint(name = "Application")
    public Application getApplication(WebServiceFeature... features) {
        return super.getPort(new QName("soa.logistic.tracking", "Application"), Application.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TRACKINGSERVICE_EXCEPTION!= null) {
            throw TRACKINGSERVICE_EXCEPTION;
        }
        return TRACKINGSERVICE_WSDL_LOCATION;
    }

}
