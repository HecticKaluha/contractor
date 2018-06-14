package JMS;

import JMS.connection.MessageReceiverGateway;
import JMS.connection.MessageSenderGateway;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class OrderProductGateway implements MessageListener {

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private String channelName;
    private Broker broker;

    public OrderProductGateway() {

    }

    public OrderProductGateway(String channelName) {
        this.channelName = channelName;
        //should be: OrderToProduct
        sender = new MessageSenderGateway(channelName);
        receiver = new MessageReceiverGateway("ProductToOrder");
        receiver.setListerner(this);
    }

    public void sendToProduct(String message) {
        TextMessage textMessage = sender.createTextMessage(message);
        sender.send(textMessage);
    }

    public void sendObjectMessageToProduct(ObjectMessage om) {
        sender.sendObjectMessage(om);
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                System.out.println("Received message from Product: " + message.toString());
                //check message for typefield and call right method


            } else {
                System.out.println("The message wasnt of the correct type. It was not an instance ObjectMessage");
            }
        } catch (Exception e) {
            //TODO: Make non-general catch
            System.out.println("Something went wrong when receiving a message in the OrderProductGateway: onMessage" + e.getMessage());
        }
    }

    public MessageSenderGateway getSender() {
        return sender;
    }

    public void setSender(MessageSenderGateway sender) {
        this.sender = sender;
    }

    public MessageReceiverGateway getReceiver() {
        return receiver;
    }

    public void setReceiver(MessageReceiverGateway receiver) {
        this.receiver = receiver;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }


}
