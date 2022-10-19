package gribanov.lab1.repository;

import gribanov.lab1.models.SessionData;
import gribanov.lab1.repository.interfaces.DisciplineDaoInterface;
import gribanov.lab1.repository.interfaces.SessionDataDaoInterface;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
@Remote(SessionDataDaoInterface.class)
public class SessionDataDao extends BaseDao implements SessionDataDaoInterface {
    @Override
    public Optional<SessionData> getById(Integer id) {
        return Optional.ofNullable(this.entityManager.find(SessionData.class, id));
    }

    @Override
    public List<SessionData> getAll() {
        TypedQuery<SessionData> query = this.entityManager.createNamedQuery(
                "SessionData.getAll", SessionData.class
        );
        return query.getResultList();
    }

    @Override
    public void save(SessionData sessionData) {
        this.entityManager.persist(sessionData);
    }

    @Override
    public void update(SessionData sessionData) {
        this.entityManager.merge(sessionData);
    }

    @Override
    public void delete(SessionData sessionData) {
        this.entityManager.remove(
                this.entityManager.contains(sessionData) ? sessionData : this.entityManager.merge(sessionData)
        );
    }
}
