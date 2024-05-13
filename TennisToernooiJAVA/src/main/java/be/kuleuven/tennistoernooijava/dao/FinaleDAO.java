package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Finales;

public class FinaleDAO implements BaseDAO<Finales, Integer> {
    @Override
    public Class<Finales> getEntityClass() {
        return Finales.class;
    }
}
