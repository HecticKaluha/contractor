package dao;

import exceptions.*;
import model.Customer;
import model.Product;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers() throws CouldNotGetCustomersException;

    Customer addCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotCreateCustomerException;

    Customer find(String name) throws CouldNotFindCustomerException;

    Customer updateCustomer(String name, String adress, String bankAccount, String sex, int age, String email) throws CouldNotFindCustomerException, CouldNotUpdateCustomerException;

    boolean delete(String name) throws CouldNotDeleteCustomerException;
}
