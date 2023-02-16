package example1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MqMessageReceiverWithException {
    public static void main(String args[]) throws JMSException, InterruptedException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("example1-queue");

        MessageConsumer consumer = session.createConsumer(destination);

        int i = 0;

        while (true) {
            TextMessage message = (TextMessage) consumer.receive();
            if (message != null) {
                String text = message.getText();
                System.out.println("consumer received:" + text);
                message.acknowledge();
                i ++;
                if (i == 1) {
                    throw new JMSException("exception");
                }
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
