package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.Supporters;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class SupporterDAO implements BaseDAO<Supporters, Integer> {
    @Override
    public Class<Supporters> getEntityClass() {
        return Supporters.class;
    }

    public List<Supporters> getSupporterFromSpeler(Spelers speler) {
        TypedQuery<Supporters> query = entityManager.createQuery(
                "SELECT s FROM Supporters s WHERE s.supporterID = :spelerID",
                Supporters.class);
        query.setParameter("spelerID", speler);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
