package gribanov.lab2.utils;

import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.models.EmailHistory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class AspectLogging {

    @Autowired
    JmsTemplate jmsTemplate;

    @AfterReturning(value = "within(gribanov.lab2.services.*) && !@annotation(NoLogging)", returning = "returnValue")
    public void logAfterExecuteCrud(JoinPoint joinPoint, Object returnValue) {
        String methodName = joinPoint.getSignature().getName();
        String[] packageSplitted = joinPoint.getTarget().getClass().getName().split("\\.", 0);
        String targetObject = packageSplitted[packageSplitted.length - 1].split("ServiceImpl")[0];
        String eventType = null;
        String arguments = Arrays.toString(joinPoint.getArgs());

        if (methodName.contains("find") || methodName.contains("getAll")) {
            eventType = EventType.SELECT_TYPE.getLabel();
            if (returnValue instanceof Optional) {
                if (!((Optional<?>) returnValue).isPresent()) {
                    String msg = "Someone is trying to access to an nonexistent property!!!";
                    jmsTemplate.convertAndSend("mail", new EmailHistory("admin", msg));
                }
            }
        } else if (methodName.equals("save")) {
            eventType = EventType.INSERT_TYPE.getLabel();
        } else if (methodName.equals("delete")) {
            eventType = EventType.DELETE_TYPE.getLabel();
        }

        jmsTemplate.convertAndSend("event", new ActionWatcher(eventType, targetObject, arguments));
    }
}
