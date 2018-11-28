
package order.logistic.soa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import app.place_order_service.OrderResponseArray;


/**
 * <p>Java class for place_orderResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="place_orderResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="place_orderResult" type="{place_order_service.app}OrderResponseArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "place_orderResponse", propOrder = {
    "placeOrderResult"
})
public class PlaceOrderResponse {

    @XmlElementRef(name = "place_orderResult", namespace = "soa.logistic.order", type = JAXBElement.class, required = false)
    protected JAXBElement<OrderResponseArray> placeOrderResult;

    /**
     * Gets the value of the placeOrderResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OrderResponseArray }{@code >}
     *     
     */
    public JAXBElement<OrderResponseArray> getPlaceOrderResult() {
        return placeOrderResult;
    }

    /**
     * Sets the value of the placeOrderResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OrderResponseArray }{@code >}
     *     
     */
    public void setPlaceOrderResult(JAXBElement<OrderResponseArray> value) {
        this.placeOrderResult = value;
    }

}
