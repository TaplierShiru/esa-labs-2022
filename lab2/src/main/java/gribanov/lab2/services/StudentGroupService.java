package gribanov.lab2.services;

import gribanov.lab2.models.StudentGroup;
import gribanov.lab2.repository.StudentGroupRepository;
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
public class StudentGroupService {
    private final StudentGroupRepository studentGroupRepository;

    @Autowired
    public StudentGroupService(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Transactional(readOnly = true)
    public List<StudentGroup> getAll() {
        return StreamSupport.stream(studentGroupRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<StudentGroup> getById(Integer id) {
        return studentGroupRepository.findById(id);
    }

    public void save(StudentGroup studentGroup) {
        studentGroupRepository.save(studentGroup);
    }

    public void delete(StudentGroup studentGroup) {
        studentGroupRepository.delete(studentGroup);
    }
}
