package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Tennisclubs;

import java.util.List;

public class TennisclubDAO implements BaseDAO<Tennisclubs, Integer> {
    @Override
    public Class<Tennisclubs> getEntityClass() {
        return Tennisclubs.class;
    }


}
