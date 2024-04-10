package example3;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.*;

public class MqMessageReceiver {
    public static void main(String args[]) throws JMSException, InterruptedException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("example3-queue");

        MessageConsumer consumer = session.createConsumer(destination);

        while (true) {
            TextMessage message = (TextMessage) consumer.receive();
            if (message != null) {
                String text = message.getText();
                System.out.println(text);
                System.out.println(message.getStringProperty("username"));
                message.acknowledge();
                session.commit();
            } else {
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();
    }
}
