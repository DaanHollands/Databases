package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Finales;
import be.kuleuven.tennistoernooijava.models.Spelers;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class FinaleDAO implements BaseDAO<Finales, Integer> {
    @Override
    public Class<Finales> getEntityClass() {
        return Finales.class;
    }

    public List<Finales> getFinalesFromSpeler(Spelers speler) {
        TypedQuery<Finales> query = entityManager.createQuery(
                "SELECT f FROM Finales f " +
                        "LEFT JOIN f.deelnamens d " +
                        "LEFT JOIN f.ballenrapers b " +
                        "WHERE d.spelerID.spelerID = :spelerID " +
                        "OR f.wedstrijdleider.speler.spelerID = :spelerID " +
                        "OR f.scheidsID.scheids.spelerID = :spelerID " +
                        "OR b.speler.spelerID = :spelerID",
                Finales.class);
        query.setParameter("spelerID", speler.getSpelerID());
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
