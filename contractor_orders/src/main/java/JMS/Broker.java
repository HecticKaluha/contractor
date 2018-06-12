package JMS;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ObjectMessage;

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

    public void sendToCustomer(ObjectMessage message) {
        orderCustomerGateway.sendToCustomer(message);
    }

    public void sendToProduct(ObjectMessage message) {
        orderProductGateway.sendToProduct(message);
    }
}
