package dao;

import exceptions.CouldNotDeleteOrderException;
import exceptions.CouldNotFindOrderException;
import exceptions.CouldNotGetOrderException;
import model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(unitName = "contractor_order")
    private EntityManager em;

    @Override
    public List<Order> getAllOrders() throws CouldNotGetOrderException {
        try {
            return em.createQuery("SELECT Orderclass FROM Orderclass orderclass").getResultList();
        } catch (Exception e) {
            throw new CouldNotGetOrderException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteOrder(int orderId) throws CouldNotDeleteOrderException {
        try{
            Order orderToDelete = find(orderId);
            em.remove(orderToDelete);
            return true;
        }
        catch(Exception e)
        {
            throw new CouldNotDeleteOrderException(e.getMessage());
        }
    }

    @Override
    public Order find(int orderId) throws CouldNotFindOrderException {
        try {
            Order orderToReturn = em.createQuery("SELECT Orderclass FROM Orderclass orderclass WHERE orderclass.orderId = :orderId" , Order.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
            return orderToReturn;
        } catch (Exception e) {
            throw new CouldNotFindOrderException(e.getMessage());
        }
    }

    @Override
    public Order addOrder(List<Integer> orders) {
        Order order = new Order(orders);
        em.persist(order);
        return order;
    }
}
