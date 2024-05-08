package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Finales;

import java.util.List;

public class FinaleDAO implements BaseDAO<Finales, Integer>{
    @Override
    public Class<Finales> getEntityClass() {
        return Finales.class;
    }

    @Override
    public Finales create(Finales entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Finales update(Finales entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Finales entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Finales find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Finales> findAll() {
        return BaseDAO.super.findAll();
    }
}
