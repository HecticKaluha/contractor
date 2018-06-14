package dao;

import JMS.Broker;
import JMS.MessageObject;
import JMS.connection.ConnectionManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import exceptions.CouldNotDeleteOrderException;
import exceptions.CouldNotFindOrderException;
import exceptions.CouldNotGetOrderException;
import model.Order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
@Stateless
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(unitName = "contractor_order")
    private EntityManager em;

    @Inject
    private Broker broker;

    @Override
    @Transactional
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
        try {
            Order orderToDelete = find(orderId);
            em.remove(orderToDelete);
            return true;
        } catch (Exception e) {
            throw new CouldNotDeleteOrderException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Order find(int orderId) throws CouldNotFindOrderException {
        try {
            Order orderToReturn = em.createQuery("SELECT Orderclass FROM Orderclass orderclass WHERE orderclass.orderId = :orderId", Order.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
            return orderToReturn;
        } catch (Exception e) {
            throw new CouldNotFindOrderException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Order addOrder(int orderid, List<Integer> products) {
        Order order = new Order(orderid, products);
        try {
            em.persist(order);
        } catch (Exception e) {
            System.out.println("Something went wrong while persisting the order");
            e.printStackTrace();
        }

        ObjectMessage om = broker.getOrderProductGateway().getSender().createObjectMessage((Serializable) products);


        try {
            om.setStringProperty("action", "calculateTotalPrice");
            om.setIntProperty("orderid", orderid);
        } catch (JMSException e) {
            System.out.println("Something went wrong when setting a property = ");
            e.printStackTrace();
        }
        broker.sendObjectMessageToProduct(om);

        return order;
    }

    @Override
    @Transactional
    public Order updateOrderPrice(int orderid, int totalprice) throws CouldNotFindOrderException {
        Order order = find(orderid);
        order.setTotalPrice(totalprice);
        em.merge(order);
        return order;
    }
}
