package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Datums;

public class DatumsDAO implements BaseDAO<Datums, Integer> {

    @Override
    public Class<Datums> getEntityClass() {
        return Datums.class;
    }
}
