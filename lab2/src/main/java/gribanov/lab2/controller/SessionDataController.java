package gribanov.lab2.controller;

import gribanov.lab2.models.SessionData;
import gribanov.lab2.services.SessionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/session-data")
public class SessionDataController {

    @Autowired
    private SessionDataService sessionDataService;

    @GetMapping
    public ResponseEntity<Object> getSessionData() {
        List<SessionData> sessionDataList = this.sessionDataService.getAll();
        return ResponseEntity.ok().body(sessionDataList);
    }

    @PostMapping
    public ResponseEntity<Object> createSessionData(@RequestBody SessionData sessionData) {
        this.sessionDataService.save(sessionData);
        return ResponseEntity.ok("New session successfully added");
    }

    @PostMapping("/{sessionDataId}")
    public ResponseEntity<Object> updateSessionData(@PathVariable("sessionDataId") String sessionDataId, @RequestBody SessionData updatedSessionData) {
        Optional<SessionData> optionalSessionData = this.sessionDataService.getById(Integer.valueOf(sessionDataId));
        if (!optionalSessionData.isPresent()) {
            return new ResponseEntity<>(
                    String.format("SessionData with id %s not found", sessionDataId), HttpStatus.NOT_FOUND
            );
        }
        SessionData sessionData = optionalSessionData.get();
        sessionData.setMark(updatedSessionData.getMark());
        this.sessionDataService.save(sessionData);
        return ResponseEntity.ok("New session successfully updated");
    }

    @DeleteMapping("/{sessionDataId}")
    public ResponseEntity<Object> updateSessionData(@PathVariable("sessionDataId") String sessionDataId) {
        Optional<SessionData> optionalSessionData = this.sessionDataService.getById(Integer.valueOf(sessionDataId));
        if (!optionalSessionData.isPresent()) {
            return new ResponseEntity<>(
                    String.format("SessionData with id %s not found", sessionDataId), HttpStatus.NOT_FOUND
            );
        }
        this.sessionDataService.delete(optionalSessionData.get());
        return ResponseEntity.ok("New session successfully deleted");
    }

}
