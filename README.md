# jboss-ejb-in-ear
1)Run "mvn clean install" to build the project.

2)Go to test module and run "mvn install -Pserver"  to launch jmsserver.

3)Go to test module and run "mvn install -Pclient" to launch jms client.

4)Look for message in jmsclient jboss log : "Attempting to read" , at this instance kill jmsserv and launch it again immediately.

5)Look  logs in jmsclient jboss for thread test-async-<1-5> to see if it got blocked on message receive.

