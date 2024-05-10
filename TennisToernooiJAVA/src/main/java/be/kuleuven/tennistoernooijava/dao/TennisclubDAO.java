package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Tennisclubs;

public class TennisclubDAO implements BaseDAO<Tennisclubs, Integer> {
    @Override
    public Class<Tennisclubs> getEntityClass() {
        return Tennisclubs.class;
    }


}
