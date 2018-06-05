package service;

import dao.CustomerDao;
import exceptions.CouldNotCreateCustomerException;
import exceptions.CouldNotGetCustomersException;
import model.Customer;

import javax.inject.Inject;
import java.util.List;

public class CustomerService {

    @Inject
    CustomerDao customerDao;

    public List<Customer> getAllCustomers() throws CouldNotGetCustomersException {
        return customerDao.getAllCustomers();
    }

    public Customer addCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotCreateCustomerException {
        return customerDao.addCustomer(name, adress, bankAccount, sex, age, email);
    }
}
