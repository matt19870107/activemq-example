package demo.consumer;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {
    @JmsListener(destination = "${queue.name}", containerFactory = "queueListenerFactory")
    public void receiveMsg(String text) {
        System.out.println("接收到消息 : "+text);
    }}
