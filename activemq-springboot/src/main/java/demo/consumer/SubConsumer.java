package demo.consumer;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SubConsumer {

    @JmsListener(destination = "${topic.name}", containerFactory = "topicListenerFactory")
    public void receiveTopic1(String text) {
        System.out.println("receiveTopic1: " + text);
    }

    @JmsListener(destination = "${topic.name}", containerFactory = "topicListenerFactory")
    public void receiveTopic2(String text) {
        System.out.println("receiveTopic2: " + text);
    }
}

