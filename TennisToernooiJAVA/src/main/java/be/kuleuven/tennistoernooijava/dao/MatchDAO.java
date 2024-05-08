package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Matchen;

import java.util.List;

public class MatchDAO implements BaseDAO<Matchen, Integer> {
    @Override
    public Class<Matchen> getEntityClass() {
        return Matchen.class;
    }


}
