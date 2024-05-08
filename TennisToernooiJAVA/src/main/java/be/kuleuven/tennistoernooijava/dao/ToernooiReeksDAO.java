package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.ToernooiReeksen;

import java.util.List;

public class ToernooiReeksDAO implements BaseDAO<ToernooiReeksen, Integer> {
    @Override
    public Class<ToernooiReeksen> getEntityClass() {
        return ToernooiReeksen.class;
    }

    @Override
    public ToernooiReeksen create(ToernooiReeksen entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public ToernooiReeksen update(ToernooiReeksen entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(ToernooiReeksen entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public ToernooiReeksen find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<ToernooiReeksen> findAll() {
        return BaseDAO.super.findAll();
    }
}
