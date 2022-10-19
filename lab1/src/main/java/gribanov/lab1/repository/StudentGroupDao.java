package gribanov.lab1.repository;

import gribanov.lab1.models.StudentGroup;
import gribanov.lab1.repository.interfaces.StudentGroupDaoInterface;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
@Remote(StudentGroupDaoInterface.class)
public class StudentGroupDao extends BaseDao implements StudentGroupDaoInterface {
    @Override
    public Optional<StudentGroup> getById(Integer id) {
        return Optional.ofNullable(this.entityManager.find(StudentGroup.class, id));
    }

    @Override
    public List<StudentGroup> getAll() {
        TypedQuery<StudentGroup> query = this.entityManager.createNamedQuery(
                "StudentGroup.getAll", StudentGroup.class
        );
        return query.getResultList();
    }

    @Override
    public void save(StudentGroup studentGroup) {
        this.entityManager.persist(studentGroup);
    }

    @Override
    public void update(StudentGroup studentGroup) {
        this.entityManager.merge(studentGroup);
    }

    @Override
    public void delete(StudentGroup studentGroup) {
        this.entityManager.remove(
                this.entityManager.contains(studentGroup) ? studentGroup : this.entityManager.merge(studentGroup)
        );
    }
}
