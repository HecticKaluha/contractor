package Initialize;

import service.CustomerService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Initialize {


    @Inject
    CustomerService customerService;

    public Initialize() {

    }

    @PostConstruct
    public void initData() {
        try {
            customerService.addCustomer("Hans", "Steenkoolfabriek", "NL RABO 03674 56 744", "male", 22, "hans@email.com");
            customerService.addCustomer("Klaartje", "Mendelssohnstraat", "NL RABO 0746 674 544", "female", 30, "klaartje@email.com");
            customerService.addCustomer("Peter", "Havikstraat", "NL RABO 03656 74 744", "male", 18, "peter@email.com");
        } catch (Exception e) {
            System.out.println("error initializing: " + e.getMessage());
        }
    }
}