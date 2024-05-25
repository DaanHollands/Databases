package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Deelnamen;
import be.kuleuven.tennistoernooijava.models.Spelers;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeelnamenDAO implements BaseDAO<Deelnamen, Integer> {
    @Override
    public Class<Deelnamen> getEntityClass() {
        return Deelnamen.class;
    }
}
