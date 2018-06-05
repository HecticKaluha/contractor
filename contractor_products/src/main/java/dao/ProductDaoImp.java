package dao;

import exceptions.*;
import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductDaoImp implements ProductDao {


    @PersistenceContext(unitName = "contractor_product")
    private EntityManager em;

    @Override
    public List<Product> getAllProducts() throws CouldNotGetProductsException {
        try {
            return em.createQuery("SELECT product FROM Product product").getResultList();
        } catch (Exception e) {
            throw new CouldNotGetProductsException(e.getMessage());
        }
    }

    @Override
    public Product addProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotCreateProductException {
        try {
            Product product = new Product(brand, model, tracks, hp, price, description);
            em.persist(product);
            return product;
        } catch (Exception e) {
            throw new CouldNotCreateProductException(e.getMessage());
        }
    }

    @Override
    public Product updateProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotFindProductException, CouldNotUpdateProductException {
        Product productToUpdate;
        try {
            productToUpdate = find(brand, model);
            productToUpdate.setTracks(tracks);
            productToUpdate.setHp(hp);
            productToUpdate.setPrice(price);
            productToUpdate.setDescription(description);
            em.persist(productToUpdate);
            return productToUpdate;
        } catch (CouldNotFindProductException e) {
            throw new CouldNotFindProductException(e.getMessage());
        } catch (Exception e) {
            throw new CouldNotUpdateProductException(e.getMessage());
        }
    }

    @Override
    public Product find(String brand, String model) throws CouldNotFindProductException {
        try {
            Product productToReturn = em.createQuery("SELECT product FROM Product product WHERE product.brand = :brand and product.model =:model", Product.class)
                    .setParameter("brand", brand)
                    .setParameter("model", model)
                    .getSingleResult();
            return productToReturn;
        } catch (Exception e) {
            throw new CouldNotFindProductException(e.getMessage());
        }
    }

    @Override
    public boolean delete(String brand, String model) throws CouldNotDeleteProductException {
        try{
            Product productToDelete = find(brand, model);
            em.remove(productToDelete);
            return true;
        }
        catch(Exception e)
        {
            throw new CouldNotDeleteProductException(e.getMessage());
        }
    }


}
