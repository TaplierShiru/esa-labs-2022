package gribanov.lab1.repository;

import gribanov.lab1.models.Specialization;
import gribanov.lab1.repository.interfaces.DaoInterface;
import gribanov.lab1.repository.interfaces.SessionDataDaoInterface;
import gribanov.lab1.repository.interfaces.SpecializationDaoInterface;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
@Remote(SpecializationDaoInterface.class)
public class SpecializationDao extends BaseDao implements SpecializationDaoInterface {
    @Override
    public Optional<Specialization> getById(Integer id) {
        return Optional.ofNullable(this.entityManager.find(Specialization.class, id));
    }

    @Override
    public List<Specialization> getAll() {
        TypedQuery<Specialization> query = this.entityManager.createNamedQuery("Specialization.getAll", Specialization.class);
        return query.getResultList();
    }

    @Override
    public void save(Specialization specialization) {
        this.entityManager.persist(specialization);
    }

    @Override
    public void update(Specialization specialization) {
        this.entityManager.merge(specialization);
    }

    @Override
    public void delete(Specialization specialization) {
        this.entityManager.remove(this.entityManager.contains(specialization) ? specialization : this.entityManager.merge(specialization));
    }
}
