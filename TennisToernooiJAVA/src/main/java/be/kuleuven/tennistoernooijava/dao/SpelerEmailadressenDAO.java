package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.models.Spelers;

public class SpelerEmailadressenDAO implements BaseDAO<SpelerEmailadressen, Spelers> {

    @Override
    public Class<SpelerEmailadressen> getEntityClass() {
        return SpelerEmailadressen.class;
    }
}
