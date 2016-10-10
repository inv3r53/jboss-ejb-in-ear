package org.jboss.as.quickstarts.ear;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class JmsClientTestIT {

    private static File[] resolveAsFilesForEar() {
        return Maven.resolver().loadPomFromFile("pom.xml").resolve("org.jboss.quickstarts.eap:jms-client:ear:?").withoutTransitivity().asFile();
    }

    public static void main(String[] args) {
        System.out.println(resolveAsFilesForEar()[0]);
    }

    @Deployment(managed = false, name = "jms-client", testable = false)
    public static EnterpriseArchive createTestDeployment() {
        return ShrinkWrap.createFromZipFile(EnterpriseArchive.class, resolveAsFilesForEar()[0]);
    }

    @Test
    @InSequence(1)
    @OperateOnDeployment("jms-client")
    public void call1() throws Exception {
        System.out.println("going to sleep now..");
        TimeUnit.MINUTES.sleep(20);
        System.out.println("Done..");
    }

}
