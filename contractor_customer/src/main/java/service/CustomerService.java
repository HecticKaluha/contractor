package service;

import dao.CustomerDao;
import exceptions.*;
import model.Customer;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
@Stateless
public class CustomerService {

    @Inject
    CustomerDao customerDao;

    public List<Customer> getAllCustomers() throws CouldNotGetCustomersException {
        return customerDao.getAllCustomers();
    }

    public Customer addCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotCreateCustomerException {
        return customerDao.addCustomer(name, adress, bankAccount, sex, age, email);
    }

    public Customer find(String name) throws CouldNotFindCustomerException {
        return customerDao.find(name);
    }

    public Customer updateCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotFindCustomerException, CouldNotUpdateCustomerException {
        return customerDao.updateCustomer(name, adress, bankAccount, sex, age, email);
    }

    public boolean delete(String name) throws CouldNotDeleteCustomerException {
        return customerDao.delete(name);
    }
}
