package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Finale;

import java.util.List;

public class FinaleDAO implements BaseDAO<Finale, Integer>{
    @Override
    public Class<Finale> getEntityClass() {
        return Finale.class;
    }

    @Override
    public Finale create(Finale entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Finale update(Finale entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Finale entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Finale find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Finale> findAll() {
        return BaseDAO.super.findAll();
    }
}
