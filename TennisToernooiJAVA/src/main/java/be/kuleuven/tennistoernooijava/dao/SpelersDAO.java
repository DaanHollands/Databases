package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.Spelers;
import org.slf4j.Logger;

public class SpelersDAO implements BaseDAO<Spelers, Integer> {

    @Override
    public Class<Spelers> getEntityClass() {
        return Spelers.class;
    }
}
