package dao;

import JMS.Broker;
import JMS.MessageObject;
import JMS.connection.ConnectionManager;
import com.google.gson.Gson;
import exceptions.CouldNotDeleteOrderException;
import exceptions.CouldNotFindOrderException;
import exceptions.CouldNotGetOrderException;
import model.Order;

import javax.inject.Inject;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(unitName = "contractor_order")
    private EntityManager em;

    @Inject
    private Broker broker;

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
    public Order addOrder(List<Integer> products) {
        Order order = new Order(products);
        try{
            em.persist(order);
        }
        catch(Exception e)
        {
            System.out.println("Foutje " + e.getMessage() );
        }


        //put message in queue to calculate the totalprice of the orders
        MessageObject messageObject = new MessageObject();
        messageObject.setAction("calculateTotalPrice");
        messageObject.addParameter(order.getProducts());
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(messageObject);
        System.out.println("The Json you sent to Product is: " + jsonMessage);
        broker.sendToProduct(jsonMessage);

        return order;
    }
}
