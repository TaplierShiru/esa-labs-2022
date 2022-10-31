package gribanov.lab2.jms;

import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.models.EmailHistory;
import gribanov.lab2.services.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsEmailReceiver implements JmsActionReceiver {

    @Autowired
    private EmailHistoryService emailHistoryService;

    @Override
    @JmsListener(destination = "event")
    public void receive(ActionWatcher event) {
        String msg = String.format("%s happened on object of type %s.", event.getAction(), event.getEntity());
        this.emailHistoryService.save( new EmailHistory("admin", msg));
        System.out.println("Received <admin> || msg = " + msg);
    }
}
