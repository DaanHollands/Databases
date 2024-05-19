package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.Reeksen;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ReeksenDAO implements BaseDAO<Reeksen, Integer> {

    @Override
    public Class<Reeksen> getEntityClass() {
        return Reeksen.class;
    }

    public Reeksen findFromNivea(ReeksenWaardes reeks, Integer niveau) {
        TypedQuery<Reeksen> query = entityManager.createQuery(
                "SELECT a FROM Reeksen a WHERE a.niveau = :niveau AND a.reeks = :reeks",
                Reeksen.class);
        query.setParameter("niveau", niveau);
        query.setParameter("reeks", reeks);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
