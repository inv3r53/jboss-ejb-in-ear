package org.jboss.as.quickstarts.ear;

import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class JmsServerTestIT {

    @Test
    public void call1() throws Exception {
        System.out.println("Sleeping now..");
        TimeUnit.MINUTES.sleep(20);
        System.out.println("Done!");
    }

}
