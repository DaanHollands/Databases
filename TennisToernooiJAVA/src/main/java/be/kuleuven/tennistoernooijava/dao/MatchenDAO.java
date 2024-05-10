package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Matchen;

public class MatchenDAO implements BaseDAO<Matchen, Integer> {
    @Override
    public Class<Matchen> getEntityClass() {
        return Matchen.class;
    }

//    public  getHighestMatch()
}
