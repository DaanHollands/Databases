package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Tennisclubs;

public class TennisclubDAO implements BaseDAO<Tennisclubs, Integer> {
    @Override
    public Class<Tennisclubs> getEntityClass() {
        return Tennisclubs.class;
    }


}
