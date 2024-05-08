package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Toernooien;

import java.util.List;

public class ToernooiDAO implements BaseDAO<Toernooien, Integer> {
    @Override
    public Class<Toernooien> getEntityClass() {
        return Toernooien.class;
    }


}
