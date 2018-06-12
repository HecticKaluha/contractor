package JMS;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ObjectMessage;

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

    public void sendToOrder(ObjectMessage message) {
        customerOrderGateway.sendToOrder(message);
    }

    public void sendToProduct(ObjectMessage message) {
        customerProductGateway.sendToProduct(message);
    }
}
