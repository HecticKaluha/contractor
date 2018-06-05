package dao;

import exceptions.CouldNotCreateCustomerException;
import exceptions.CouldNotGetCustomersException;
import model.Customer;
import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CustomerDaoImp implements CustomerDao {

    @PersistenceContext(unitName = "contractor_customer")
    private EntityManager em;

    @Override
    public List<Customer> getAllCustomers() throws CouldNotGetCustomersException {
        try {
            return em.createQuery("SELECT customer FROM Customer customer").getResultList();
        } catch (Exception e) {
            throw new CouldNotGetCustomersException(e.getMessage());
        }
    }

    @Override
    public Customer addCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotCreateCustomerException {
        try {
            Customer customer = new Customer(name, adress, bankAccount, sex, age, email);
            em.persist(customer);
            return customer;
        } catch (Exception e) {
            throw new CouldNotCreateCustomerException(e.getMessage());
        }
    }


}
