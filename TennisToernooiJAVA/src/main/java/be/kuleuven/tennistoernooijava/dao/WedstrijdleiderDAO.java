package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Wedstrijdleiders;

import java.util.List;

public class WedstrijdleiderDAO implements BaseDAO<Wedstrijdleiders, Integer>{
    @Override
    public Class<Wedstrijdleiders> getEntityClass() {
        return Wedstrijdleiders.class;
    }
    
}
