package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Datum;

import java.util.List;

public class DatumDAO implements BaseDAO<Datum, Integer>{

    @Override
    public Class<Datum> getEntityClass() {
        return Datum.class;
    }

    @Override
    public Datum create(Datum entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Datum update(Datum entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Datum entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Datum find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Datum> findAll() {
        return BaseDAO.super.findAll();
    }
}
