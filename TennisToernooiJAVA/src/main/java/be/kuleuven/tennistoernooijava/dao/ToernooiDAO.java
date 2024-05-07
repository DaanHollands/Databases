package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Toernooi;

import java.util.List;

public class ToernooiDAO implements BaseDAO<Toernooi, Integer> {
    @Override
    public Class<Toernooi> getEntityClass() {
        return Toernooi.class;
    }

    @Override
    public Toernooi create(Toernooi entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Toernooi update(Toernooi entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Toernooi entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Toernooi find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Toernooi> findAll() {
        return BaseDAO.super.findAll();
    }
}
