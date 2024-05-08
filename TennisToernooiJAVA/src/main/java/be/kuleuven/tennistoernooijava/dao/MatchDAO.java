package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Matchen;

import java.util.List;

public class MatchDAO implements BaseDAO<Matchen, Integer> {
    @Override
    public Class<Matchen> getEntityClass() {
        return Matchen.class;
    }

    @Override
    public Matchen create(Matchen entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Matchen update(Matchen entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Matchen entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Matchen find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Matchen> findAll() {
        return BaseDAO.super.findAll();
    }
}
