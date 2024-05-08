package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Toernooien;

import java.util.List;

public class ToernooiDAO implements BaseDAO<Toernooien, Integer> {
    @Override
    public Class<Toernooien> getEntityClass() {
        return Toernooien.class;
    }

    @Override
    public Toernooien create(Toernooien entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Toernooien update(Toernooien entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Toernooien entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Toernooien find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Toernooien> findAll() {
        return BaseDAO.super.findAll();
    }
}
