package service;

import dao.OrderDao;
import exceptions.CouldNotDeleteOrderException;
import exceptions.CouldNotGetOrderException;

import javax.inject.Inject;
import model.Order;
import java.util.List;

public class OrderService {

    @Inject
    private OrderDao orderDao;

    public List<Order> getAllOrders() throws CouldNotGetOrderException {
        return orderDao.getAllOrders();
    }

    public boolean deleteOrder(int orderId) throws CouldNotDeleteOrderException {
        return orderDao.deleteOrder(orderId);
    }

    public Order addOrder(List<Integer> products){
        return orderDao.addOrder(products);
    }
}
