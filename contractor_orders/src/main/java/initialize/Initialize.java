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
        order1.add(1);
        order1.add(3);
        List<Integer> order3 = new ArrayList<Integer>();
        order1.add(1);

        try {
            orderService.addOrder(order1);
            orderService.addOrder(order2);
            orderService.addOrder(order3);
        } catch (Exception e) {
            System.out.println("error initializing: " + e.getMessage());
        }
    }
}