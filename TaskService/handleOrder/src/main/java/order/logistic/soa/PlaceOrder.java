
package order.logistic.soa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import app.place_order_service.OrderRequestArray;


/**
 * <p>Java class for place_order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="place_order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="order_requests" type="{place_order_service.app}OrderRequestArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "place_order", propOrder = {
    "orderRequests"
})
public class PlaceOrder {

    @XmlElementRef(name = "order_requests", namespace = "soa.logistic.order", type = JAXBElement.class, required = false)
    protected JAXBElement<OrderRequestArray> orderRequests;

    /**
     * Gets the value of the orderRequests property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OrderRequestArray }{@code >}
     *     
     */
    public JAXBElement<OrderRequestArray> getOrderRequests() {
        return orderRequests;
    }

    /**
     * Sets the value of the orderRequests property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OrderRequestArray }{@code >}
     *     
     */
    public void setOrderRequests(JAXBElement<OrderRequestArray> value) {
        this.orderRequests = value;
    }

}
