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

}
