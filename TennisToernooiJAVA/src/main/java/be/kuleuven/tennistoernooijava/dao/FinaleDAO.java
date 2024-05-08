package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Finales;

import java.util.List;

public class FinaleDAO implements BaseDAO<Finales, Integer>{
    @Override
    public Class<Finales> getEntityClass() {
        return Finales.class;
    }


}
