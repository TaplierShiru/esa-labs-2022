package gribanov.lab2.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ListenerFactoryImpl implements ListenerFactory {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public EventListener createEmailListener() {
        return new EmailHistoryListener(jmsTemplate);
    }

    @Override
    public EventListener createEventLoggerListener() {
        return new EventLoggerListener(jmsTemplate);
    }
}
