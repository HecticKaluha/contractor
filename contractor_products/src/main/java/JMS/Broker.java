package JMS;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@Singleton
@Startup
public class Broker {
    private ProductOrderGateway productOrderGateway;
    private ProductCustomerGateway productCustomerGateway;

    public Broker() {
    }

    @PostConstruct
    public void start()
    {
        System.out.println("Starting Product broker");
        productOrderGateway = new ProductOrderGateway("ProductToOrder");
        productOrderGateway.setBroker(this);

        productCustomerGateway = new ProductCustomerGateway("ProductToCustomer");
        productCustomerGateway.setBroker(this);
    }

    public void sendToOrder(TextMessage message) {
        productOrderGateway.sendToOrder(message);
    }

    public void sendToCustomer(TextMessage message) {
        productCustomerGateway.sendToCustomer(message);
    }
}
