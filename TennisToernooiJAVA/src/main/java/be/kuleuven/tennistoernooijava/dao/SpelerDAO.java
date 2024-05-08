package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Spelers;

import java.util.List;

public class SpelerDAO implements BaseDAO<Spelers, Integer> {

    @Override
    public Class<Spelers> getEntityClass() {
        return Spelers.class;
    }

}
