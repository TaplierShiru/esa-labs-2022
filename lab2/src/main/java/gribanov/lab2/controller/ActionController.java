package gribanov.lab2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.models.EmailHistory;
import gribanov.lab2.services.ActionWatcherService;
import gribanov.lab2.services.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/actions")
public class ActionController {
    @Autowired
    EmailHistoryService emailService;

    @Autowired
    ActionWatcherService actionWatcherService;

    @GetMapping("/emails")
    public ResponseEntity<Object> getEmail() throws JsonProcessingException {
        List<EmailHistory> emails = emailService.findAll();
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(emails));
    }

    @GetMapping("/events")
    public ResponseEntity<Object> getEvents() throws JsonProcessingException {
        List<ActionWatcher> events = actionWatcherService.findAll();
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(events));
    }

}
