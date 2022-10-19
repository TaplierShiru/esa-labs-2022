package gribanov.lab2.jms;

public interface ListenerFactory {
    EventListener createEmailListener();
    EventListener createEventLoggerListener();
}
