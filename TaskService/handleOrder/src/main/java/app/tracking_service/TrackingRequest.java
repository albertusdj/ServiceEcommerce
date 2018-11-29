
package app.tracking_service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrackingRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackingRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="secret_key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="order_unique_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackingRequest", propOrder = {
    "secretKey",
    "orderUniqueId"
})
public class TrackingRequest {

    @XmlElementRef(name = "secret_key", namespace = "tracking_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<String> secretKey;
    @XmlElementRef(name = "order_unique_id", namespace = "tracking_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<String> orderUniqueId;

    /**
     * Gets the value of the secretKey property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecretKey() {
        return secretKey;
    }

    /**
     * Sets the value of the secretKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecretKey(JAXBElement<String> value) {
        this.secretKey = value;
    }

    /**
     * Gets the value of the orderUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrderUniqueId() {
        return orderUniqueId;
    }

    /**
     * Sets the value of the orderUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrderUniqueId(JAXBElement<String> value) {
        this.orderUniqueId = value;
    }

}
