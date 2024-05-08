package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Reeksen;

import java.util.List;

public class ReeksDAO implements BaseDAO<Reeksen, Integer>{
    @Override
    public Class<Reeksen> getEntityClass() {
        return Reeksen.class;
    }

    @Override
    public Reeksen create(Reeksen entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Reeksen update(Reeksen entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Reeksen entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Reeksen find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Reeksen> findAll() {
        return BaseDAO.super.findAll();
    }
}
