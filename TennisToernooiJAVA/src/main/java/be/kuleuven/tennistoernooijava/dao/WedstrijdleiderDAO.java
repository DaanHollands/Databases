package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.models.Wedstrijdleider;

public class WedstrijdleiderDAO implements BaseDAO<Wedstrijdleider, Integer> {
    @Override
    public Class<Wedstrijdleider> getEntityClass() {
        return Wedstrijdleider.class;
    }
}
