
package app.place_order_service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sender_secret_key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="from_lat" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="from_lng" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="receiver_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additional_detail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderRequest", propOrder = {
    "senderSecretKey",
    "fromLat",
    "fromLng",
    "destination",
    "weight",
    "receiverName",
    "additionalDetail"
})
public class OrderRequest {

    @XmlElementRef(name = "sender_secret_key", namespace = "place_order_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderSecretKey;
    @XmlElementRef(name = "from_lat", namespace = "place_order_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> fromLat;
    @XmlElementRef(name = "from_lng", namespace = "place_order_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> fromLng;
    @XmlElementRef(name = "destination", namespace = "place_order_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destination;
    @XmlElementRef(name = "weight", namespace = "place_order_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> weight;
    @XmlElementRef(name = "receiver_name", namespace = "place_order_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<String> receiverName;
    @XmlElementRef(name = "additional_detail", namespace = "place_order_service.app", type = JAXBElement.class, required = false)
    protected JAXBElement<String> additionalDetail;

    /**
     * Gets the value of the senderSecretKey property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderSecretKey() {
        return senderSecretKey;
    }

    /**
     * Sets the value of the senderSecretKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderSecretKey(JAXBElement<String> value) {
        this.senderSecretKey = value;
    }

    /**
     * Gets the value of the fromLat property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getFromLat() {
        return fromLat;
    }

    /**
     * Sets the value of the fromLat property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setFromLat(JAXBElement<Double> value) {
        this.fromLat = value;
    }

    /**
     * Gets the value of the fromLng property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getFromLng() {
        return fromLng;
    }

    /**
     * Sets the value of the fromLng property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setFromLng(JAXBElement<Double> value) {
        this.fromLng = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestination(JAXBElement<String> value) {
        this.destination = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setWeight(JAXBElement<Double> value) {
        this.weight = value;
    }

    /**
     * Gets the value of the receiverName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReceiverName() {
        return receiverName;
    }

    /**
     * Sets the value of the receiverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReceiverName(JAXBElement<String> value) {
        this.receiverName = value;
    }

    /**
     * Gets the value of the additionalDetail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdditionalDetail() {
        return additionalDetail;
    }

    /**
     * Sets the value of the additionalDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdditionalDetail(JAXBElement<String> value) {
        this.additionalDetail = value;
    }

}
