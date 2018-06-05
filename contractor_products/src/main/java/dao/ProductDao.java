package dao;

import exceptions.*;
import model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts() throws CouldNotGetProductsException;

    Product addProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotCreateProductException;

    Product updateProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotFindProductException, CouldNotUpdateProductException;

    Product find(String brand, String model) throws CouldNotFindProductException;

    boolean delete(String brand, String model) throws CouldNotDeleteProductException;
}
