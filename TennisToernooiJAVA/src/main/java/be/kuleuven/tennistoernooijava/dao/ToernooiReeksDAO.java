package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.ToernooiReeksen;

import java.util.List;

public class ToernooiReeksDAO implements BaseDAO<ToernooiReeksen, Integer> {
    @Override
    public Class<ToernooiReeksen> getEntityClass() {
        return ToernooiReeksen.class;
    }


}
