package se.iths.grocerylist.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import se.iths.grocerylist.config.JmsConfig;
import se.iths.grocerylist.model.MessageObject;

@Component
public class Receiver {

    @JmsListener(destination = JmsConfig.GROCERYLOGIN_QUEUE)
    public void listen(@Payload MessageObject messageObject){
        System.out.println("I got a message");
        System.out.println(messageObject);
    }

}
