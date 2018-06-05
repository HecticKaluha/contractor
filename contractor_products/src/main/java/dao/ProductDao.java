package dao;

import exceptions.CouldNotCreateProductException;
import model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts() throws CouldNotCreateProductException;
    Product addProduct(String name) throws CouldNotCreateProductException;
}
