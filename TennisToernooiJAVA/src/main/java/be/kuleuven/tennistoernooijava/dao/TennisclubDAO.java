package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Tennisclub;

import java.util.List;

public class TennisclubDAO implements BaseDAO<Tennisclub, Integer> {
    @Override
    public Class<Tennisclub> getEntityClass() {
        return Tennisclub.class;
    }

    @Override
    public Tennisclub create(Tennisclub entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Tennisclub update(Tennisclub entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Tennisclub entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Tennisclub find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Tennisclub> findAll() {
        return BaseDAO.super.findAll();
    }
}
