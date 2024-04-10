package example2;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.*;

public class TopicSubscriber {
    // ActiveMQ服务地址
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    // 主题名称，取消息必须和存消息的主题名称一致
    public static final String TOPIC_NAME = "example2-topic";

    public static void main(String[] args) throws Exception {
        // 1.创建给定ActiveMQ服务连接工厂，使用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.通过连接工厂，创建连接对象并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3.创建会话，第一个参数为是否开启事务，第二个参数为签收
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 4.创建目的地（队列或者主题）
        Topic topic = session.createTopic(TOPIC_NAME);
        // 5.创建消费者
        MessageConsumer consumer = session.createConsumer(topic);

        while (true) {
            // 接受消息根据生产者发送消息类型强类型转换
            TextMessage message = (TextMessage) consumer.receive();
            if (message != null) {
                String text = message.getText();
                System.out.println("topic receiver1: " + text);
            } else {
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();
    }
}
