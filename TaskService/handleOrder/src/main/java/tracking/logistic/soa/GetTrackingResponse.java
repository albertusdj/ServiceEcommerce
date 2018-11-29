
package tracking.logistic.soa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import app.tracking_service.TrackingResponse;


/**
 * <p>Java class for get_trackingResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="get_trackingResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="get_trackingResult" type="{tracking_service.app}TrackingResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "get_trackingResponse", propOrder = {
    "getTrackingResult"
})
public class GetTrackingResponse {

    @XmlElementRef(name = "get_trackingResult", namespace = "soa.logistic.tracking", type = JAXBElement.class, required = false)
    protected JAXBElement<TrackingResponse> getTrackingResult;

    /**
     * Gets the value of the getTrackingResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TrackingResponse }{@code >}
     *     
     */
    public JAXBElement<TrackingResponse> getGetTrackingResult() {
        return getTrackingResult;
    }

    /**
     * Sets the value of the getTrackingResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TrackingResponse }{@code >}
     *     
     */
    public void setGetTrackingResult(JAXBElement<TrackingResponse> value) {
        this.getTrackingResult = value;
    }

}
