package gribanov.lab2.jms;

import gribanov.lab2.models.ActionWatcher;

public interface JmsActionReceiver {
    void receive(ActionWatcher event);
}
