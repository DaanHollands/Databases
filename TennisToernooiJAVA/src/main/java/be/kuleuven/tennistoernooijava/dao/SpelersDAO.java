package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Spelers;

public class SpelersDAO implements BaseDAO<Spelers, Integer> {

    @Override
    public Class<Spelers> getEntityClass() {
        return Spelers.class;
    }
}
