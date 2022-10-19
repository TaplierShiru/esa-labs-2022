package gribanov.lab1.repository;

import gribanov.lab1.models.Teacher;
import gribanov.lab1.repository.interfaces.TeacherDaoInterface;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Session;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
@Remote(TeacherDaoInterface.class)
public class TeacherDao extends BaseDao implements TeacherDaoInterface {
    @Override
    public Optional<Teacher> getById(Integer id) {
        return Optional.ofNullable(this.entityManager.find(Teacher.class, id));
    }

    @Transactional
    @Override
    public List<Teacher> getAll() {
        return this.entityManager.createNamedQuery(
                "Teacher.getAll", Teacher.class
        ).getResultList();
    }

    @Override
    public void save(Teacher teacher) {
        this.entityManager.persist(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        this.entityManager.merge(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        this.entityManager.remove(
                this.entityManager.contains(teacher) ? teacher : this.entityManager.merge(teacher)
        );
    }
}
