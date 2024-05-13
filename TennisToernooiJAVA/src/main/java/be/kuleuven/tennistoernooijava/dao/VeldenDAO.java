package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Velden;

public class VeldenDAO implements BaseDAO<Velden, Integer> {
    @Override
    public Class<Velden> getEntityClass() {
        return Velden.class;
    }
}
