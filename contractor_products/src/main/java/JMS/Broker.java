package JMS;

import exceptions.CouldNotCalculatePriceException;
import service.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.util.Arrays;
import java.util.List;

@Singleton
@Startup
public class Broker {
    private ProductOrderGateway productOrderGateway;
    private ProductCustomerGateway productCustomerGateway;

    @Inject
    ProductService productService;

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

    public int calculateTotalPrice(List<Integer> products){
        try {
            return productService.calculateTotalPrice(products);
        } catch (CouldNotCalculatePriceException e) {
            System.out.println("Something went wrong while caluculating the price : " + e.getMessage());
        }
        return -1;
    }
}
