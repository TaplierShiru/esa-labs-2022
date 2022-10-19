package gribanov.lab2.jms;

import gribanov.lab2.models.ActionWatcher;

public interface EventListener {
    void update(String eventType, ActionWatcher event);
}
