
package tracking.logistic.soa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import app.tracking_service.TrackingRequestArray;


/**
 * <p>Java class for get_tracking complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="get_tracking">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tracking_request" type="{tracking_service.app}TrackingRequestArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "get_tracking", propOrder = {
    "trackingRequest"
})
public class GetTracking {

    @XmlElementRef(name = "tracking_request", namespace = "soa.logistic.tracking", type = JAXBElement.class, required = false)
    protected JAXBElement<TrackingRequestArray> trackingRequest;

    /**
     * Gets the value of the trackingRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TrackingRequestArray }{@code >}
     *     
     */
    public JAXBElement<TrackingRequestArray> getTrackingRequest() {
        return trackingRequest;
    }

    /**
     * Sets the value of the trackingRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TrackingRequestArray }{@code >}
     *     
     */
    public void setTrackingRequest(JAXBElement<TrackingRequestArray> value) {
        this.trackingRequest = value;
    }

}
