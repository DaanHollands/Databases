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

}
