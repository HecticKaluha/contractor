package JMS.connection;

import exception.CouldNotCreateConnectionException;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;

public class ConnectionManager {

    private static ConnectionManager instance;


    private ConnectionManager() {

    }

    public static ConnectionManager getInstance()
    {
        if(instance != null)
        {
            instance = new ConnectionManager();
        }
        return instance;

    }
    public static Connection getNewConnection() throws CouldNotCreateConnectionException {
        Connection connection;
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            //connectionFactory.setTrustedPackages(new ArrayList<>(Arrays.asList("mix.model.loan.LoanReply,mix.model.loan.LoanRequest".split(","))));
            connectionFactory.setTrustAllPackages(true);

            connection = connectionFactory.createConnection();
        }
        catch (JMSException e){
            throw new CouldNotCreateConnectionException(e.getMessage());
        }
        return connection;
    }
}