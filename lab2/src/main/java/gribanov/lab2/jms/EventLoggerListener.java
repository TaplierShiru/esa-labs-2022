package gribanov.lab2.jms;

import gribanov.lab2.models.ActionWatcher;
import org.springframework.jms.core.JmsTemplate;

public class EventLoggerListener implements EventListener {

    JmsTemplate jmsTemplate;

    public EventLoggerListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void update(String eventType, ActionWatcher event) {
        jmsTemplate.convertAndSend("event", event);
    }
}
