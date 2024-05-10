package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Reeksen;

public class ReeksenDAO implements BaseDAO<Reeksen, Integer> {

    @Override
    public Class<Reeksen> getEntityClass() {
        return Reeksen.class;
    }
}
