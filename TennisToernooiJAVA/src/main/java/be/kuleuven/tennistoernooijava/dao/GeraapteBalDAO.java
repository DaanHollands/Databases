package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.GeraapteBal;

import java.util.List;

public class GeraapteBalDAO implements BaseDAO<GeraapteBal, Integer> {
    @Override
    public Class<GeraapteBal> getEntityClass() {
        return GeraapteBal.class;
    }

    @Override
    public GeraapteBal create(GeraapteBal entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public GeraapteBal update(GeraapteBal entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(GeraapteBal entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public GeraapteBal find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<GeraapteBal> findAll() {
        return BaseDAO.super.findAll();
    }
}
