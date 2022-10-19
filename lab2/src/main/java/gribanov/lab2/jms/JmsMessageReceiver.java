package gribanov.lab2.jms;

import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.models.EmailHistory;
import gribanov.lab2.services.ActionWatcherService;
import gribanov.lab2.services.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageReceiver {

    @Autowired
    EmailHistoryService emailHistoryService;

    @Autowired
    ActionWatcherService actionWatcherService;

    @JmsListener(destination = "mail")
    public void receiveEmail(EmailHistory email) {
        this.emailHistoryService.save(email);
        System.out.println("Received <" + email + ">");
    }

    @JmsListener(destination = "event")
    public void receiveWatcherEvent(ActionWatcher event) {
        this.actionWatcherService.save(event);
        System.out.println("Received <" + event + ">");
    }
}
