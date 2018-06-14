package initialize;

import service.OrderService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class Initialize {
    
    @Inject
    OrderService orderService;

    public Initialize() {

    }

    @PostConstruct
    public void initData() {
        List<Integer> order1 = new ArrayList<Integer>();
        order1.add(1);
        order1.add(2);
        order1.add(3);
        List<Integer> order2 = new ArrayList<Integer>();
        order2.add(1);
        order2.add(3);
        List<Integer> order3 = new ArrayList<Integer>();
        order3.add(1);

        try {
            System.out.println("started initializing orders");
            orderService.addOrder(1, order1);
            orderService.addOrder(2, order2);
            orderService.addOrder(3, order3);
        } catch (Exception e) {
            System.out.println("error initializing: " + e.getMessage());
        }
    }
}