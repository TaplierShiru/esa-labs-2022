package gribanov.lab2.jms;

import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.services.ActionWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class JmsEventReceiver implements JmsActionReceiver{

    @Autowired
    private ActionWatcherService actionWatcherService;

    @Override
    @JmsListener(destination = "event")
    public void receive(ActionWatcher event) {
        this.actionWatcherService.save(event);
        System.out.println("Received <" + event + ">");
    }
}
