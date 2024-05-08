package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.GeraapteBallen;

import java.util.List;

public class GeraapteBalDAO implements BaseDAO<GeraapteBallen, Integer> {
    @Override
    public Class<GeraapteBallen> getEntityClass() {
        return GeraapteBallen.class;
    }

    @Override
    public GeraapteBallen create(GeraapteBallen entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public GeraapteBallen update(GeraapteBallen entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(GeraapteBallen entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public GeraapteBallen find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<GeraapteBallen> findAll() {
        return BaseDAO.super.findAll();
    }
}
