package service;

import dao.ProductDao;
import exceptions.*;
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

    public Product updateProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotUpdateProductException, CouldNotFindProductException {
        return productDao.updateProduct(brand, model, tracks, hp, price, description);
    }

    public Product find(String brand, String model) throws CouldNotFindProductException {
        return productDao.find(brand, model);
    }

    public boolean delete(String brand, String model) throws CouldNotDeleteProductException {
        return productDao.delete(brand, model);
    }
}
