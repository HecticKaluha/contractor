package service;

import dao.ProductDao;
import exceptions.CouldNotCreateProductException;
import exceptions.CouldNotGetProductsException;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductService {

    @Inject
    ProductDao productDao;

    public List<Product> getAllProducts() throws CouldNotGetProductsException {
        return productDao.getAllProducts();
    }

    public Product addProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotCreateProductException {
        return productDao.addProduct(brand, model, tracks, hp, price, description);
    }

}
