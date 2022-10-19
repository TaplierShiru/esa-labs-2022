package gribanov.lab2.services;

import gribanov.lab2.models.EmailHistory;
import gribanov.lab2.repository.EmailHistoryRepository;
import gribanov.lab2.utils.NoLogging;
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
public class EmailHistoryService {

    private final EmailHistoryRepository repository;

    @Autowired
    public EmailHistoryService(EmailHistoryRepository repository) {
        this.repository = repository;
    }

    @NoLogging
    public Optional<EmailHistory> findById(Integer id) {
        return repository.findById(id);
    }

    @NoLogging
    public List<EmailHistory> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @NoLogging
    public void save(EmailHistory email) {
        repository.save(email);
    }

    @NoLogging
    public void delete(EmailHistory email) {
        repository.delete(email);
    }
}