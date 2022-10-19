package gribanov.lab2.repository;

import gribanov.lab2.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> { }
