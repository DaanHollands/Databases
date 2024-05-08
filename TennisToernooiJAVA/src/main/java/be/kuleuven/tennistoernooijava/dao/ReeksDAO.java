package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Reeksen;

import java.util.List;

public class ReeksDAO implements BaseDAO<Reeksen, Integer>{
    @Override
    public Class<Reeksen> getEntityClass() {
        return Reeksen.class;
    }


}
