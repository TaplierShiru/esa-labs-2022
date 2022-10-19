package gribanov.lab2.services;

import gribanov.lab2.jms.EventManager;
import gribanov.lab2.jms.ListenerFactory;
import gribanov.lab2.models.ActionWatcher;
import gribanov.lab2.models.Teacher;
import gribanov.lab2.repository.TeacherRepository;
import gribanov.lab2.utils.EventType;
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
public class TeacherService {
    private final TeacherRepository teacherRepository;
    //private final EventManager eventManager;
    //private final ListenerFactory listenerFactory;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) { // ListenerFactory listenerFactory
        this.teacherRepository = teacherRepository;
        /*
        this.eventManager = new EventManager("get", "save", "delete");
        this.eventManager.subscribe("get", listenerFactory.createEventLoggerListener());
        this.eventManager.subscribe("save", listenerFactory.createEmailListener());
        this.eventManager.subscribe("save", listenerFactory.createEventLoggerListener());
        this.eventManager.subscribe("delete", listenerFactory.createEmailListener());
        this.eventManager.subscribe("delete", listenerFactory.createEventLoggerListener());
        */
    }

    @Transactional(readOnly = true)
    public List<Teacher> getAll() {
        // this.eventManager.notify("get", new ActionWatcher(EventType.SELECT_TYPE.getLabel(), "Teacher", ""));
        return StreamSupport.stream(teacherRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Teacher> getById(Integer id) {
        // this.eventManager.notify("get", new ActionWatcher(EventType.SELECT_TYPE.getLabel(), "Teacher", ""));
        return teacherRepository.findById(id);
    }

    public void save(Teacher teacher) {
        // this.eventManager.notify("save", new ActionWatcher(EventType.INSERT_TYPE.getLabel(), "Teacher", ""));
        teacherRepository.save(teacher);
    }

    public void delete(Teacher teacher) {
        // this.eventManager.notify("delete", new ActionWatcher(EventType.DELETE_TYPE.getLabel(), "Teacher", ""));
        teacherRepository.delete(teacher);
    }
}
