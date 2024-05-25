package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Reeksen;
import be.kuleuven.tennistoernooijava.models.Spelers;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReeksenDAO implements BaseDAO<Reeksen, Integer> {

    @Override
    public Class<Reeksen> getEntityClass() {
        return Reeksen.class;
    }

    public Reeksen findFromNivea(ReeksenWaardes reeks, Integer niveau) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("niveau", niveau);
        parameters.put("reeks", reeks);
        return executeQuery(
                "SELECT a FROM Reeksen a WHERE a.niveau = :niveau AND a.reeks = :reeks",
                Reeksen.class,
                parameters
        ).get(0);
    }
}
