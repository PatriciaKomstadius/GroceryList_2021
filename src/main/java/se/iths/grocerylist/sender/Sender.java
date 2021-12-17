package se.iths.grocerylist.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.iths.grocerylist.config.JmsConfig;
import se.iths.grocerylist.model.MessageObject;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class Sender {


    JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String name) {
        System.out.println("User created: " + name);
        MessageObject messageObject = new MessageObject(UUID.randomUUID(), "User created: " + name, LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.GROCERYLOGIN_QUEUE, messageObject);
        System.out.println("Message sent!");

    }


}
