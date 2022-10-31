package gribanov.lab2.utils;

import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.models.EmailHistory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {
    JmsTemplate jmsTemplate;
    Topic topic;

    @Autowired
    public AspectLogging(JmsTemplate jmsTemplate) throws JMSException {
        this.jmsTemplate = jmsTemplate;
        this.topic = jmsTemplate.getConnectionFactory().createConnection().createSession().createTopic("event");
    }

    @AfterReturning(value = "within(gribanov.lab2.services.*) && !@annotation(NoLogging)", returning = "returnValue")
    public void logAfterExecuteCrud(JoinPoint joinPoint, Object returnValue) throws JMSException {
        String methodName = joinPoint.getSignature().getName();
        String[] packageSplitted = joinPoint.getTarget().getClass().getName().split("\\.", 0);
        String targetObject = packageSplitted[packageSplitted.length - 1].split("ServiceImpl")[0];
        String eventType = EventType.UNKNOWN_TYPE.getLabel();
        String arguments = Arrays.toString(joinPoint.getArgs());

        if (methodName.contains("find") || methodName.contains("getAll")) {
            eventType = EventType.SELECT_TYPE.getLabel();
        } else if (methodName.equals("save")) {
            eventType = EventType.INSERT_TYPE.getLabel();
        } else if (methodName.equals("delete")) {
            eventType = EventType.DELETE_TYPE.getLabel();
        }

        jmsTemplate.convertAndSend(this.topic, new ActionWatcher(eventType, targetObject, arguments));
    }
}
