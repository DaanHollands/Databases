package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Toernooien;

public class ToernooienDAO implements BaseDAO<Toernooien, Integer> {
    @Override
    public Class<Toernooien> getEntityClass() {
        return Toernooien.class;
    }
}
