package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
// 开启jms服务
@EnableJms
public class BootQueueMain {
    public static void main(String[] args) {
        SpringApplication.run(BootQueueMain.class, args);
    }
}
