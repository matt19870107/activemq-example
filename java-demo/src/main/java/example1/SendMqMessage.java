package example1;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.*;

public class SendMqMessage {
    public static void main (String args[]) throws JMSException, InterruptedException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("example1-queue");

        MessageProducer producer = session.createProducer(destination);
        producer.setTimeToLive(600000);
        for(int i = 0; i<3; i++) {
            TextMessage message = session.createTextMessage("message-" + i);

            Thread.sleep(1000);
            producer.send(message);
        }

        session.commit();;
        session.close();
        connection.close();
    }
}
