package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Deelnamen;

public class DeelnamenDAO implements BaseDAO<Deelnamen, Integer> {
    @Override
    public Class<Deelnamen> getEntityClass() {
        return Deelnamen.class;
    }
}
