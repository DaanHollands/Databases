package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Adressen;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

public class AdresDAO implements BaseDAO<Adressen, Integer>{
    @Override
    public Class<Adressen> getEntityClass() {
        return Adressen.class;
    }

    public Adressen getAdresFrom(Integer postcode, String straatnaam, Integer straatnummer) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("postcode", postcode);
        parameters.put("straatnaam", straatnaam);
        parameters.put("straatnummer", straatnummer);

        return executeQuery(
                "SELECT a FROM Adressen a " +
                        "WHERE a.postcode = :postcode " +
                        "AND a.straatnaam = :straatnaam " +
                        "AND a.straatnummer = :straatnummer",
                Adressen.class,
                parameters
        ).get(0);
    }
}
