package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchenDAO implements BaseDAO<Matchen, Integer> {
    @Override
    public Class<Matchen> getEntityClass() {
        return Matchen.class;
    }

    public List<Matchen> getMatchenFrom(Spelers speler) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("spelerID", speler.getSpelerID());
        return executeQuery(
                "SELECT m FROM Matchen m " +
                        "LEFT JOIN m.deelnamens d " +
                        "WHERE d.spelerID.spelerID = :spelerID",
                Matchen.class,
                parameters
        );
    }

    public List<Matchen> getMatchenAndFinalesFrom(Spelers speler) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("spelerID", speler.getSpelerID());
        return executeQuery(
                "SELECT DISTINCT m FROM Matchen m " +
                        "LEFT JOIN FETCH m.deelnamens d " +
                        "WHERE d.spelerID.spelerID = :spelerID " +
                        "OR m.toernooiID.wedstrijdleider.speler.spelerID = :spelerID",
                Matchen.class,
                parameters
        );
    }
}
