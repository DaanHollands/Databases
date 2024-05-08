package be.kuleuven.tennistoernooijava.dao;
import be.kuleuven.tennistoernooijava.model.Adressen;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdresDAO implements BaseDAO<Adressen, Integer>{
    @Override
    public Class<Adressen> getEntityClass() {
        return Adressen.class;
    }


    public Adressen getAdresFrom(Integer postcode, String straatnaam, Integer straatnummer) {
        TypedQuery<Adressen> query = entityManager.createQuery(
                "SELECT a FROM Adressen a WHERE a.postcode = :postcode AND a.straatnaam = :straatnaam AND a.straatnummer = :straatnummer",
                Adressen.class);
        query.setParameter("postcode", postcode);
        query.setParameter("straatnaam", straatnaam);
        query.setParameter("straatnummer", straatnummer);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
