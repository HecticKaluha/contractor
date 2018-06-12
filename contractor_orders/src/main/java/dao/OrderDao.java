package dao;

import exceptions.CouldNotDeleteOrderException;
import exceptions.CouldNotFindOrderException;
import exceptions.CouldNotGetOrderException;

import model.Order;
import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders() throws CouldNotGetOrderException;
    boolean deleteOrder(int orderId) throws CouldNotDeleteOrderException;
    Order find(int orderId) throws CouldNotFindOrderException;
    Order addOrder(List<Integer> orders);

}
