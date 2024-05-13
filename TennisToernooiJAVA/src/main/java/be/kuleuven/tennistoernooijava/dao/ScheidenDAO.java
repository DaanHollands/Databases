package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Scheidsen;

public class ScheidenDAO implements BaseDAO<Scheidsen, Integer> {
    @Override
    public Class<Scheidsen> getEntityClass() {
        return Scheidsen.class;
    }
}
