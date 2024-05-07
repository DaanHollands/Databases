package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Veld;

import java.util.List;

public class VeldDAO implements BaseDAO<Veld, Integer> {
    @Override
    public Class<Veld> getEntityClass() {
        return Veld.class;
    }

    @Override
    public Veld create(Veld entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Veld update(Veld entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Veld entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Veld find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Veld> findAll() {
        return BaseDAO.super.findAll();
    }
}
