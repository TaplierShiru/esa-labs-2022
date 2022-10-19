package gribanov.lab1.repository;

import gribanov.lab1.models.Student;
import gribanov.lab1.repository.interfaces.SpecializationDaoInterface;
import gribanov.lab1.repository.interfaces.StudentDaoInterface;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
@Remote(StudentDaoInterface.class)
public class StudentDao extends BaseDao implements StudentDaoInterface {
    @Override
    public Optional<Student> getById(Integer id) {
        return Optional.ofNullable(this.entityManager.find(Student.class, id));
    }

    @Override
    public List<Student> getAll() {
        TypedQuery<Student> query = this.entityManager.createNamedQuery(
                "Student.getAll", Student.class
        );
        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    public void update(Student student) {
        this.entityManager.merge(student);
    }

    @Override
    public void delete(Student student) {
        this.entityManager.remove(
                this.entityManager.contains(student) ? student : this.entityManager.merge(student)
        );
    }
}
