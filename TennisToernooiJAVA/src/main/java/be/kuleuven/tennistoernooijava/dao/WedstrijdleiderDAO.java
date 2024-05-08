package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Wedstrijdleiders;

import java.util.List;

public class WedstrijdleiderDAO implements BaseDAO<Wedstrijdleiders, Integer>{
    @Override
    public Class<Wedstrijdleiders> getEntityClass() {
        return Wedstrijdleiders.class;
    }

    @Override
    public Wedstrijdleiders create(Wedstrijdleiders entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Wedstrijdleiders update(Wedstrijdleiders entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Wedstrijdleiders entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Wedstrijdleiders find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Wedstrijdleiders> findAll() {
        return BaseDAO.super.findAll();
    }
}
