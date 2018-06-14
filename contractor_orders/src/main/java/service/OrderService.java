package service;

import dao.OrderDao;
import exceptions.CouldNotDeleteOrderException;
import exceptions.CouldNotFindOrderException;
import exceptions.CouldNotGetOrderException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import exceptions.OrderAlreadyPaidException;
import model.Order;
import java.util.List;

@Stateless
public class OrderService {

    @Inject
    private OrderDao orderDao;

    public List<Order> getAllOrders() throws CouldNotGetOrderException {
        return orderDao.getAllOrders();
    }

    public boolean deleteOrder(int orderId) throws CouldNotDeleteOrderException {
        return orderDao.deleteOrder(orderId);
    }

    public Order addOrder(int orderid, List<Integer> products, String name){
        return orderDao.addOrder(orderid, products, name);
    }

    public Order find(int orderId) throws CouldNotFindOrderException {
        return orderDao.find(orderId);
    }

    public Order updateOrderPrice(int orderid, int totalprice) throws CouldNotFindOrderException {
        return orderDao.updateOrderPrice(orderid, totalprice);
    }

    public void payOrder(int orderToPay) throws CouldNotFindOrderException, OrderAlreadyPaidException {
        orderDao.payOrder(orderToPay);
    }
}
