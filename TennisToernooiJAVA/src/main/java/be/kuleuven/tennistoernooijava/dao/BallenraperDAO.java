package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Ballenraper;

import java.util.List;

public class BallenraperDAO implements BaseDAO<Ballenraper, Integer> {
    @Override
    public Class<Ballenraper> getEntityClass() {
        return Ballenraper.class;
    }

    @Override
    public Ballenraper create(Ballenraper entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Ballenraper update(Ballenraper entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Ballenraper entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Ballenraper find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Ballenraper> findAll() {
        return BaseDAO.super.findAll();
    }
}
