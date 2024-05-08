package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.GeraapteBallen;

import java.util.List;

public class GeraapteBalDAO implements BaseDAO<GeraapteBallen, Integer> {
    @Override
    public Class<GeraapteBallen> getEntityClass() {
        return GeraapteBallen.class;
    }


}
