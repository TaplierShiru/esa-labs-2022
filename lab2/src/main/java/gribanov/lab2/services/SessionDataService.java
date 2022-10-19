package gribanov.lab2.services;

import gribanov.lab2.models.SessionData;
import gribanov.lab2.repository.SessionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Repository
@Transactional
public class SessionDataService {
    private final SessionDataRepository sessionDataRepository;

    @Autowired
    public SessionDataService(SessionDataRepository sessionDataRepository) {
        this.sessionDataRepository = sessionDataRepository;
    }

    @Transactional(readOnly = true)
    public List<SessionData> getAll() {
        return StreamSupport.stream(sessionDataRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<SessionData> getById(Integer id) {
        return sessionDataRepository.findById(id);
    }

    public void save(SessionData sessionData) {
        sessionDataRepository.save(sessionData);
    }

    public void delete(SessionData sessionData) {
        sessionDataRepository.delete(sessionData);
    }
}
