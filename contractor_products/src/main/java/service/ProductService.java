package service;

import dao.ProductDao;
import exceptions.CouldNotCreateProductException;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductService {

    @Inject
    ProductDao productDao;

    public List<Product> getAllProducts() throws CouldNotCreateProductException {
        return productDao.getAllProducts();
    }

    public Product addProduct(String name) throws CouldNotCreateProductException {
        return productDao.addProduct(name);
    }

}
