package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.SpelersEmailadressen;

import java.util.List;

public class SpelersEmailAdresDAO implements BaseDAO<SpelersEmailadressen, Integer> {
    @Override
    public Class<SpelersEmailadressen> getEntityClass() {
        return SpelersEmailadressen.class;
    }


}
