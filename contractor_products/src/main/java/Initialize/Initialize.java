package Initialize;

import model.Product;
import service.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;

@Singleton
@Startup
public class Initialize {


    @Inject
    ProductService productService;

    public Initialize() {

    }

    @PostConstruct
    public void initData() {
        try {
            productService.addProduct("John Deere");
            productService.addProduct("Massey Furgeson");
            productService.addProduct("New Holland");
            productService.addProduct("Case");
        } catch (Exception e) {
            System.out.println("error initializing: " + e.getMessage());
        }
    }
}