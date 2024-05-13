package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Ballenrapers;
import be.kuleuven.tennistoernooijava.models.Spelers;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class BallenraperDAO implements BaseDAO<Ballenrapers, Integer> {
    @Override
    public Class<Ballenrapers> getEntityClass() {
        return Ballenrapers.class;
    }

    public List<Ballenrapers> getBallenraperFromSpeler(Spelers speler) {
        TypedQuery<Ballenrapers> query = entityManager.createQuery(
                "SELECT b FROM Ballenrapers b WHERE b.speler = :spelerID",
                Ballenrapers.class);
        query.setParameter("spelerID", speler);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
