package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.database.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.database.Spelers;

public class SpelerEmailadressenDAO implements BaseDAO<SpelerEmailadressen, Spelers> {

    @Override
    public Class<SpelerEmailadressen> getEntityClass() {
        return SpelerEmailadressen.class;
    }
}
