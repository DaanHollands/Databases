package be.kuleuven.tennistoernooijava.dao;

import org.hibernate.criterion.CriteriaQuery;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BaseDAO<T, ID> {
    EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("be.kuleuven.tennistoernooijava.databasePU");
    EntityManager entityManager = sessionFactory.createEntityManager();

    Class<T> getEntityClass();

    default T create(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
            return null;
        }
    }

    default T update(T entity) {
        try {
            entityManager.getTransaction().begin();
            var merge = entityManager.merge(entity);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return merge;
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
            return null;
        }

    }

    default void delete(T entity) {
        try {
            entityManager.remove(entity);
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
        }
    }

    default T find(ID id) {
        var result = entityManager.find(getEntityClass(), id);
        return result;
    }

    default List<T> findAll() {
        return entityManager.createQuery("FROM " + getEntityClass().getName()).getResultList();
    }

    default <T> List<T> executeQuery(String qlString, Class<T> resultClass, Map<String, Object> parameters) {
        TypedQuery<T> query = entityManager.createQuery(qlString, resultClass);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
