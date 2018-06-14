package JMS.connection;


import com.google.gson.Gson;
import exception.CouldNotCreateConnectionException;

import javax.jms.*;
import java.io.Serializable;

public class MessageSenderGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    public MessageSenderGateway(String channelName) {
        try {
            this.connection = ConnectionManager.getNewConnection();
            connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.destination = session.createQueue(channelName);
            this.producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException | CouldNotCreateConnectionException e) {
            System.out.print("Something went wrong while creating the MessageSenderGateway because of " + e.getMessage());
        }

    }

    public ObjectMessage createObjectMessage(Serializable object) {
        try {
            return session.createObjectMessage(object);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TextMessage createTextMessage(Serializable object) {
        try {
            Gson g = new Gson();
            String ticketJSON = g.toJson(object);
            return session.createTextMessage(ticketJSON);
        }
        catch(JMSException e){
            System.out.print("Something went wrong while creating the TextMessage in MessageSenderGateway because of " + e.getMessage());
        }
        return null;
    }

    public void send(TextMessage msg) {
        try {
            producer.send(destination, msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public void sendObjectMessage(ObjectMessage msg) {
        try {
            producer.send(destination, msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendTextMessage(TextMessage msg) {
        try {
            producer.send(destination, msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}