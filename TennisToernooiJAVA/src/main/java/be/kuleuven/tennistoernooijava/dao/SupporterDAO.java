package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Supporters;

import java.util.List;

public class SupporterDAO implements BaseDAO<Supporters, Integer> {
    @Override
    public Class<Supporters> getEntityClass() {
        return Supporters.class;
    }


}
