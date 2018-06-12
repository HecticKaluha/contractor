package JMS;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@Singleton
@Startup
public class Broker {
    private OrderCustomerGateway orderCustomerGateway;
    private OrderProductGateway orderProductGateway;

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
}
