package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Velden;

import java.util.List;

public class VeldDAO implements BaseDAO<Velden, Integer> {
    @Override
    public Class<Velden> getEntityClass() {
        return Velden.class;
    }


}
