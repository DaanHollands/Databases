package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Adressen;
import be.kuleuven.tennistoernooijava.database.Reeksen;
import be.kuleuven.tennistoernooijava.database.ReeksenPK;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ReeksenDAO implements BaseDAO<Reeksen, ReeksenPK> {

    @Override
    public Class<Reeksen> getEntityClass() {
        return Reeksen.class;
    }

    public Reeksen findFromNivea(String niveau) {
        TypedQuery<Reeksen> query = entityManager.createQuery(
                "SELECT a FROM Reeksen a WHERE a.niveau = :niveau ",
                Reeksen.class);
        query.setParameter("niveau", niveau);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
