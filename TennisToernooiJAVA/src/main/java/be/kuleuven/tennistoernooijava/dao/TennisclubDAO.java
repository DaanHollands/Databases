package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Tennisclubs;

import java.util.List;

public class TennisclubDAO implements BaseDAO<Tennisclubs, Integer> {
    @Override
    public Class<Tennisclubs> getEntityClass() {
        return Tennisclubs.class;
    }

    @Override
    public Tennisclubs create(Tennisclubs entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Tennisclubs update(Tennisclubs entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Tennisclubs entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Tennisclubs find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Tennisclubs> findAll() {
        return BaseDAO.super.findAll();
    }
}
