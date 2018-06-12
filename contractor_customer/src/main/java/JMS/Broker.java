package JMS;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@Singleton
@Startup
public class Broker {
    private CustomerOrderGateway customerOrderGateway;
    private CustomerProductGateway customerProductGateway;

    public Broker() {

    }

    @PostConstruct
    public void start()
    {
        System.out.println("Starting Customer broker");
        customerOrderGateway = new CustomerOrderGateway("CustomerToOrder");
        customerOrderGateway.setBroker(this);

        customerProductGateway = new CustomerProductGateway("CustomerToProduct");
        customerProductGateway.setBroker(this);
    }

    public void sendToOrder(TextMessage message) {
        customerOrderGateway.sendToOrder(message);
    }

    public void sendToProduct(TextMessage message) {
        customerProductGateway.sendToProduct(message);
    }
}
