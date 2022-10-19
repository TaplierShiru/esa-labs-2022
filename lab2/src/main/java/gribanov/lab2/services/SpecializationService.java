package gribanov.lab2.services;

import gribanov.lab2.models.Specialization;
import gribanov.lab2.repository.SpecializationRepository;
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
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Transactional(readOnly = true)
    public List<Specialization> getAll() {
        return StreamSupport.stream(specializationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Specialization> getById(Integer id) {
        return specializationRepository.findById(id);
    }

    public void save(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    public void delete(Specialization specialization) {
        specializationRepository.delete(specialization);
    }
}
