package gribanov.lab1.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.function.Consumer;

public abstract class BaseDao {

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    public BaseDao() {}
    public BaseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        /**
         * Decorator for reducing boilerplate code
         */
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
            action.accept(getEntityManager());
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

}
