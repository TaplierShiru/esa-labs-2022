package gribanov.lab2.services;

import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.repository.ActionWatcherRepository;
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
@Transactional
@Repository
public class ActionWatcherService {

    private final ActionWatcherRepository repository;

    @Autowired
    public ActionWatcherService(ActionWatcherRepository repository) {
        this.repository = repository;
    }

    @NoLogging
    public Optional<ActionWatcher> findById(Integer id) {
        return repository.findById(id);
    }

    @NoLogging
    public List<ActionWatcher> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @NoLogging
    public void save(ActionWatcher watcherEvent) {
        repository.save(watcherEvent);
    }

    @NoLogging
    public void delete(ActionWatcher watcherEvent) {
        repository.delete(watcherEvent);
    }
}


