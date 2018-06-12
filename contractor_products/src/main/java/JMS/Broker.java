package JMS;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ObjectMessage;

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
        System.out.println("Starting Customer broker");
        productOrderGateway = new ProductOrderGateway("ProductToOrder");
        productOrderGateway.setBroker(this);

        productCustomerGateway = new ProductCustomerGateway("ProductToCustomer");
        productCustomerGateway.setBroker(this);
    }

    public void sendToOrder(ObjectMessage message) {
        productOrderGateway.sendToOrder(message);
    }

    public void sendToCustomer(ObjectMessage message) {
        productCustomerGateway.sendToCustomer(message);
    }
}
