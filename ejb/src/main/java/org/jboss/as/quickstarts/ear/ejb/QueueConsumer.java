package org.jboss.as.quickstarts.ear.ejb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

/**
 * @author inv3r53
 * 
 */
public class QueueConsumer {

    private static final String CONNECTION_FACTORY = "java:/ConnectionFactory";
    private Connection connection = null;
    private Session session = null;
    private MessageConsumer messageConsumer = null;

    public void initialize() {

        try {

            final InitialContext context = new InitialContext();
            final ConnectionFactory connFactory = (ConnectionFactory) context.lookup(CONNECTION_FACTORY);
            connection = connFactory.createConnection();
            connection.setExceptionListener(new MyExceptionListener(connection));
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final String channelJNDIName = "java:/queue/TestQueue";
            final Queue queue = (Queue) context.lookup(channelJNDIName);
            messageConsumer = session.createConsumer(queue);
            connection.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getQueuedRequest(final long consumerTimeout) {
        try {
            ObjectMessage poppedRequest = null;
            if (messageConsumer != null) {
                poppedRequest = (ObjectMessage) messageConsumer.receive(consumerTimeout);
            }
            if (poppedRequest == null) {

                return null;
            }

            return poppedRequest.getObject();

        } catch (final JMSException e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {
        if (session != null) {
            try {
                session.close();
            } catch (final JMSException e) {

                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (final JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
