package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Wedstrijdleider;

import java.util.List;

public class WedstrijdleiderDAO implements BaseDAO<Wedstrijdleider, Integer>{
    @Override
    public Class<Wedstrijdleider> getEntityClass() {
        return Wedstrijdleider.class;
    }

    @Override
    public Wedstrijdleider create(Wedstrijdleider entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Wedstrijdleider update(Wedstrijdleider entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Wedstrijdleider entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Wedstrijdleider find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Wedstrijdleider> findAll() {
        return BaseDAO.super.findAll();
    }
}
