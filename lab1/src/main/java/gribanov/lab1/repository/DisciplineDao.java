package gribanov.lab1.repository;

import gribanov.lab1.models.Discipline;
import gribanov.lab1.repository.interfaces.DisciplineDaoInterface;
import gribanov.lab1.repository.interfaces.StudentGroupDaoInterface;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
@Remote(DisciplineDaoInterface.class)
public class DisciplineDao extends BaseDao implements DisciplineDaoInterface {
    @Override
    public Optional<Discipline> getById(Integer id) {
        return Optional.ofNullable(this.entityManager.find(Discipline.class, id));
    }

    @Override
    public List<Discipline> getAll() {
        TypedQuery<Discipline> query = this.entityManager.createNamedQuery(
                "Discipline.getAll", Discipline.class
        );
        return query.getResultList();
    }

    @Override
    public void save(Discipline discipline) {
        this.entityManager.persist(discipline);
    }

    @Override
    public void update(Discipline discipline) {
        this.entityManager.merge(discipline);
    }

    @Override
    public void delete(Discipline discipline) {
        this.entityManager.remove(
                this.entityManager.contains(discipline) ? discipline : this.entityManager.merge(discipline)
        );
    }
}
