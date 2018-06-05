package dao;

import exceptions.CouldNotCreateProductException;
import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductDaoImp implements ProductDao{


    @PersistenceContext(unitName = "contractor_customer")
    private EntityManager em;

    @Override
    public List<Product> getAllProducts() throws CouldNotCreateProductException {
        try{
            return em.createQuery("SELECT product FROM Product product").getResultList();
        }
        catch(Exception e)
        {
            throw new CouldNotCreateProductException(e.getMessage());
        }
    }

    @Override
    public Product addProduct(String name) throws CouldNotCreateProductException {
        try{
            Product product = new Product(name);
            em.persist(product);
            return product;
        }
        catch(Exception e)
        {
            throw new CouldNotCreateProductException(e.getMessage());
        }

    }
}
