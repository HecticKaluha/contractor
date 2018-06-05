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
            productService.addProduct("John Deere", "9370R", false, 370, 70000, "This is a 8 wheel tractor, perfect for agriculture on small or big scale.");
            productService.addProduct("Massey Furgeson","5700", false, 110, 50000, "A beautifully designed Massey Frugeson in MF Red, 4 wheels. Compact but powerful!");
            productService.addProduct("New Holland", "T5.105", false, 107, 45000,"De T5-serie is ontworpen als de ultieme allrounder voor moderne gemengde landbouwbedrijven.");
            productService.addProduct("Case", "Farmall 35C", false, 55, 17000,"In case you need a tracter Case is the best case scenario.");
        } catch (Exception e) {
            System.out.println("error initializing: " + e.getMessage());
        }
    }
}