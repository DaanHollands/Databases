package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Velden;

import java.util.List;

public class VeldDAO implements BaseDAO<Velden, Integer> {
    @Override
    public Class<Velden> getEntityClass() {
        return Velden.class;
    }

    @Override
    public Velden create(Velden entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Velden update(Velden entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Velden entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Velden find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Velden> findAll() {
        return BaseDAO.super.findAll();
    }
}
