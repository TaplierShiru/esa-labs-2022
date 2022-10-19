package gribanov.lab2.jms;

import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.models.EmailHistory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailHistoryListener implements EventListener {

    JmsTemplate jmsTemplate;

    public EmailHistoryListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void update(String eventType, ActionWatcher event) {
        String msg = String.format("%s happend on object of type %s.", eventType, event.getEntity());
        jmsTemplate.convertAndSend("mail", new EmailHistory("admin", msg));
    }
}
