package be.kuleuven.tennistoernooijava.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface BaseDAO<T, ID> {
    EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("be.kuleuven.tennistoernooijava.databasePU");
    EntityManager entityManager = sessionFactory.createEntityManager();

    Class<T> getEntityClass(
    );

    default T create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    default T update(T entity) {
        var merge = entityManager.merge(entity);
        return merge;
    }

    default void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    default T find(ID id) {
        var result = entityManager.find(getEntityClass(), id);
        return result;
    }

    default List<T> findAll() {
        return entityManager.createQuery("FROM " + getEntityClass().getName()).getResultList();
    }
}
