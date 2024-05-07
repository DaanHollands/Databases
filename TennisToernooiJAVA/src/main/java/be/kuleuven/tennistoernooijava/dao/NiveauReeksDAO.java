package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.NiveauReeks;

import java.util.List;

public class NiveauReeksDAO implements BaseDAO<NiveauReeks, Integer> {
    @Override
    public Class<NiveauReeks> getEntityClass() {
        return NiveauReeks.class;
    }

    @Override
    public NiveauReeks create(NiveauReeks entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public NiveauReeks update(NiveauReeks entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(NiveauReeks entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public NiveauReeks find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<NiveauReeks> findAll() {
        return BaseDAO.super.findAll();
    }
}
