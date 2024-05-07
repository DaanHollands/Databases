package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.ToernooiReeks;

import java.util.List;

public class ToernooiReeksDAO implements BaseDAO<ToernooiReeks, Integer> {
    @Override
    public Class<ToernooiReeks> getEntityClass() {
        return ToernooiReeks.class;
    }

    @Override
    public ToernooiReeks create(ToernooiReeks entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public ToernooiReeks update(ToernooiReeks entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(ToernooiReeks entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public ToernooiReeks find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<ToernooiReeks> findAll() {
        return BaseDAO.super.findAll();
    }
}
