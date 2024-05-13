package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MatchenDAO implements BaseDAO<Matchen, Integer> {
    @Override
    public Class<Matchen> getEntityClass() {
        return Matchen.class;
    }

    public List<Matchen> getMatchenFrom(Spelers speler) {
        TypedQuery<Matchen> query = entityManager.createQuery(
                "SELECT m FROM Matchen m " +
                        "LEFT JOIN m.deelnamens d " +
                        "LEFT JOIN m.wedstrijdleider w " +
                        "WHERE d.spelerID.spelerID = :spelerID " +
                        "OR w.speler.spelerID = :spelerID",
                Matchen.class);
        query.setParameter("spelerID", speler.getSpelerID());
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
