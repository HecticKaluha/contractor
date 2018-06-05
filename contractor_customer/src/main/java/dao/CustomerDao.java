package dao;

import exceptions.CouldNotCreateCustomerException;
import exceptions.CouldNotGetCustomersException;
import model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers() throws CouldNotGetCustomersException;

    Customer addCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotCreateCustomerException;
}
