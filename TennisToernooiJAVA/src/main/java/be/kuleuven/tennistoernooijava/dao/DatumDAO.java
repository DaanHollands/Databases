package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Datums;

import java.util.List;

public class DatumDAO implements BaseDAO<Datums, Integer> {

    @Override
    public Class<Datums> getEntityClass() {
        return Datums.class;
    }

}