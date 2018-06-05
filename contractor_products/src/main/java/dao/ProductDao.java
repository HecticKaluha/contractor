package dao;

import exceptions.CouldNotCreateProductException;
import exceptions.CouldNotGetProductsException;
import model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts() throws CouldNotGetProductsException;
    Product addProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotCreateProductException;
}
