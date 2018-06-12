package JMS;

import JMS.connection.MessageReceiverGateway;
import JMS.connection.MessageSenderGateway;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ProductOrderGateway implements MessageListener {

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private String channelName;
    private Broker broker;

    public ProductOrderGateway() {

    }

    public ProductOrderGateway(String channelName) {
        this.channelName = channelName;
        //should be ProductToOrder
        sender = new MessageSenderGateway(channelName);
        receiver = new MessageReceiverGateway("OrderToProduct");
        receiver.setListerner(this);
    }

    public void sendToOrder(ObjectMessage message) {
        sender.send(message);
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                System.out.println("Received message from Order: " + message.toString());
                //check message for typefield and call right method


            } else {
                System.out.println("The message wasnt of the correct type. It was not an instance of ObjectMessage");
            }
        } catch (Exception e) {
            //TODO: Make non-general catch
            System.out.println("Something went wrong when receiving a message in the ProductOrderGateway: onMessage" + e.getMessage());
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
