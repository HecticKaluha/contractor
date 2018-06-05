package dao;

import exceptions.CouldNotCreateProductException;
import exceptions.CouldNotGetProductsException;
import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductDaoImp implements ProductDao{


    @PersistenceContext(unitName = "contractor_product")
    private EntityManager em;

    @Override
    public List<Product> getAllProducts() throws CouldNotGetProductsException {
        try{
            return em.createQuery("SELECT product FROM Product product").getResultList();
        }
        catch(Exception e)
        {
            throw new CouldNotGetProductsException(e.getMessage());
        }
    }

    @Override
    public Product addProduct(String brand, String model, boolean tracks, int hp, int price, String description) throws CouldNotCreateProductException {
        try{
            Product product = new Product(brand, model, tracks, hp, price, description);
            em.persist(product);
            return product;
        }
        catch(Exception e)
        {
            throw new CouldNotCreateProductException(e.getMessage());
        }
    }
}
