package org.jboss.as.quickstarts.ear.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * @author inv3r53
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProcessQueue {

    /**
     * 
     * @param timerContext
     */
    @Asynchronous
    public void process(Object info) {

        System.out.println("*********Started Processing " + info + "****************");
        QueueConsumer consumer = null;
        try {
            consumer = new QueueConsumer();
            consumer.initialize();
            System.out.println("Attempt to Read message");
            Object poppedRequest = consumer.getQueuedRequest(20000);
            System.out.println("message==>" + poppedRequest);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (consumer != null) {
                consumer.destroy();
            }
        }

        System.out.println("*****************completed processing " + info + "***********************");

    }

}
