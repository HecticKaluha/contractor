package dao;

import exceptions.*;
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

    @Override
    public Customer find(String name) throws CouldNotFindCustomerException {
        try {
            Customer CustomerToReturn = em.createQuery("SELECT customer FROM Customer customer WHERE customer.name = :name", Customer.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return CustomerToReturn;
        } catch (Exception e) {
            throw new CouldNotFindCustomerException(e.getMessage());
        }
    }

    @Override
    public Customer updateCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotFindCustomerException, CouldNotUpdateCustomerException {
        Customer customerToUpdate;
        try {
            customerToUpdate = find(name);
            customerToUpdate.setAdress(adress);
            customerToUpdate.setBankAccount(bankAccount);
            customerToUpdate.setSex(sex);
            customerToUpdate.setAge(age);
            customerToUpdate.setEmail(email);
            em.persist(customerToUpdate);
            return customerToUpdate;
        } catch (CouldNotFindCustomerException e) {
            throw new CouldNotFindCustomerException(e.getMessage());
        } catch (Exception e) {
            throw new CouldNotUpdateCustomerException(e.getMessage());
        }
    }

    @Override
    public boolean delete(String name) throws CouldNotDeleteCustomerException {
        try{
            Customer customerToDelete = find(name);
            em.remove(customerToDelete);
            return true;
        }
        catch(Exception e)
        {
            throw new CouldNotDeleteCustomerException(e.getMessage());
        }
    }


}
