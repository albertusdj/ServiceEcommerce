
package order.logistic.soa;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import app.place_order_service.OrderRequestArray;
import app.place_order_service.OrderResponseArray;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Application", targetNamespace = "soa.logistic.order")
@XmlSeeAlso({
    app.place_order_service.ObjectFactory.class,
    order.logistic.soa.ObjectFactory.class
})
public interface Application {


    /**
     * order_requests = list(order_requests)
     *         grouped_requests = {}
     *         for order_request in order_requests:
     *             if grouped_requests.get(order_request.sender_secret_key) is None:
     *                 grouped_requests[order_request.sender_secret_key] = []
     * 
     *             grouped_requests[order_request.sender_secret_key].append(order_request)
     * 
     *         responses = []
     * 
     *         for key, order_requests in grouped_requests.items():
     *             headers = {'Authorization': order_requests[0].sender_secret_key}
     *             data = {
     *                 'emp_id': 1,
     *                 "from_lat": order_requests[0].from_lat,
     *                 'from_lng': order_requests[0].from_lng,
     *                 'additional_detail': '',
     *                 'items': []
     *             }
     *             for order_request in order_requests:
     *                 item = {
     *                     "receiver_name": order_request.receiver_name,
     *                     'weight': order_request.weight,
     *                     'to_lat': float(order_request.destination.split(',')[0]),
     *                     'to_lng': float(order_request.destination.split(',')[1])
     *                 }
     * 
     *                 data['additional_detail'] += order_request.additional_detail
     * 
     *                 data['items'].append(item)
     * 
     *             response = requests.post(BASE_URL + 'order', json=data, headers={'Authorization' : order_requests[0].sender_secret_key}).json()
     *             for _ in range(len(order_requests)):
     *                 if response.get('error'):
     *                     responses.append(OrderResponse(
     *                         status="Fail: "+response.get('error'),
     *                         order_unique_id="fail" #response['unique_id']
     *                     ))
     *                 else:
     *                     responses.append(OrderResponse(
     *                         status="Success",
     *                         order_unique_id=response['unique_id']
     *                     ))
     * 
     *         return responses
     * 
     * @param orderRequests
     * @return
     *     returns app.place_order_service.OrderResponseArray
     */
    @WebMethod(operationName = "place_order", action = "place_order")
    @WebResult(name = "place_orderResult", targetNamespace = "soa.logistic.order")
    @RequestWrapper(localName = "place_order", targetNamespace = "soa.logistic.order", className = "order.logistic.soa.PlaceOrder")
    @ResponseWrapper(localName = "place_orderResponse", targetNamespace = "soa.logistic.order", className = "order.logistic.soa.PlaceOrderResponse")
    public OrderResponseArray placeOrder(
        @WebParam(name = "order_requests", targetNamespace = "soa.logistic.order")
        OrderRequestArray orderRequests);

}