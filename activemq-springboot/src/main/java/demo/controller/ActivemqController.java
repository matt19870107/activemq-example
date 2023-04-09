package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;


@RestController
public class ActivemqController {
    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send/queue")
    public String sendQueue(String name) {
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(queue, name);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
        return "Done!";
    }

    @RequestMapping("send/sub")
    public String sendSub(String name) {
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(topic, name);
        return "Done!";
    }
}
