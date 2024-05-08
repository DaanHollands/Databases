package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.NiveauReeks;

import java.util.List;

public class NiveauReeksDAO implements BaseDAO<NiveauReeks, Integer> {
    @Override
    public Class<NiveauReeks> getEntityClass() {
        return NiveauReeks.class;
    }


}
