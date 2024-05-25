package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Finales;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinaleDAO implements BaseDAO<Finales, Integer>{
    @Override
    public Class<Finales> getEntityClass() {
        return Finales.class;
    }

    public List<Finales> getFinalesFromSpeler(Spelers speler) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("spelerID", speler.getSpelerID());
        return executeQuery(
                "SELECT f FROM Finales f " +
                        "LEFT JOIN f.deelnamens d " +
                        "WHERE d.spelerID.spelerID = :spelerID ",
                Finales.class,
                parameters
        );
    }
}
