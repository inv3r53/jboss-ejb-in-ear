package org.jboss.as.quickstarts.ear.ejb;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

/**
 * @author inv3r53
 * 
 */
public class MyExceptionListener implements ExceptionListener {
    Connection conn;

    public MyExceptionListener(final Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public void onException(final JMSException exception) {
        try {
            conn.close();
        } catch (final JMSException e) {
            e.printStackTrace();
        }

    }

}
