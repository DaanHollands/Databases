package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Supporterde;

import java.util.List;

public class SupporterdeDAO implements BaseDAO<Supporterde, Integer> {
    @Override
    public Class<Supporterde> getEntityClass() {
        return Supporterde.class;
    }


}
