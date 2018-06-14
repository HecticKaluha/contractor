package JMS;

import exceptions.CouldNotFindOrderException;
import exceptions.OrderAlreadyPaidException;
import service.OrderService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@Singleton
@Startup
@Stateless
public class Broker {
    private OrderCustomerGateway orderCustomerGateway;
    private OrderProductGateway orderProductGateway;

    @Inject
    private OrderService orderService;

    public Broker() {

    }

    @PostConstruct
    public void start()
    {
        System.out.println("Starting Order broker");
        orderCustomerGateway = new OrderCustomerGateway("OrderToCustomer");
        orderCustomerGateway.setBroker(this);

        orderProductGateway = new OrderProductGateway("OrderToProduct");
        orderProductGateway.setBroker(this);
    }

    public void sendToCustomer(TextMessage message) {
        orderCustomerGateway.sendToCustomer(message);
    }

    public void sendToProduct(String message) {
        orderProductGateway.sendToProduct(message);
    }
    public void sendObjectMessageToProduct(ObjectMessage om)
    {
        orderProductGateway.sendObjectMessageToProduct(om);
    }

    public OrderCustomerGateway getOrderCustomerGateway() {
        return orderCustomerGateway;
    }

    public void setOrderCustomerGateway(OrderCustomerGateway orderCustomerGateway) {
        this.orderCustomerGateway = orderCustomerGateway;
    }

    public OrderProductGateway getOrderProductGateway() {
        return orderProductGateway;
    }

    public void setOrderProductGateway(OrderProductGateway orderProductGateway) {
        this.orderProductGateway = orderProductGateway;
    }

    public void updateOrderTotalPrice(int orderid, int totalprice) throws CouldNotFindOrderException {
        orderService.updateOrderPrice(orderid, totalprice);
    }

    public void payOrder(int orderToPay) throws CouldNotFindOrderException, OrderAlreadyPaidException {
        orderService.payOrder(orderToPay);
    }
}
