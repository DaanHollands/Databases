package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Reeks;

import java.util.List;

public class ReeksDAO implements BaseDAO<Reeks, Integer>{
    @Override
    public Class<Reeks> getEntityClass() {
        return Reeks.class;
    }

    @Override
    public Reeks create(Reeks entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Reeks update(Reeks entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Reeks entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Reeks find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Reeks> findAll() {
        return BaseDAO.super.findAll();
    }
}
