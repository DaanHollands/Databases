package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Datums;

import java.util.List;

public class DatumDAO implements BaseDAO<Datums, Integer>{

    @Override
    public Class<Datums> getEntityClass() {
        return Datums.class;
    }

    @Override
    public Datums create(Datums entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Datums update(Datums entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Datums entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Datums find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Datums> findAll() {
        return BaseDAO.super.findAll();
    }
}
