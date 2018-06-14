package dao;

import exceptions.CouldNotDeleteOrderException;
import exceptions.CouldNotFindOrderException;
import exceptions.CouldNotGetOrderException;

import exceptions.OrderAlreadyPaidException;
import model.Order;
import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders() throws CouldNotGetOrderException;
    boolean deleteOrder(int orderId) throws CouldNotDeleteOrderException;
    Order find(int orderId) throws CouldNotFindOrderException;
    Order addOrder(int orderid, List<Integer> orders, String name);
    Order updateOrderPrice(int orderid, int totalprice) throws CouldNotFindOrderException;

    void payOrder(int orderToPay) throws CouldNotFindOrderException, OrderAlreadyPaidException;
}
