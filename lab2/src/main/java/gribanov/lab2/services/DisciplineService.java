package gribanov.lab2.services;

import gribanov.lab2.models.Discipline;
import gribanov.lab2.repository.DisciplineRepository;
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
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    @Autowired
    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @Transactional(readOnly = true)
    public List<Discipline> getAll() {
        return StreamSupport.stream(disciplineRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Discipline> getById(Integer id) {
        return disciplineRepository.findById(id);
    }

    public void save(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    public void delete(Discipline discipline) {
        disciplineRepository.delete(discipline);
    }
}
