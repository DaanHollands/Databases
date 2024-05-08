package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Ballenrapers;

import java.util.List;

public class BallenraperDAO implements BaseDAO<Ballenrapers, Integer> {
    @Override
    public Class<Ballenrapers> getEntityClass() {
        return Ballenrapers.class;
    }

    @Override
    public Ballenrapers create(Ballenrapers entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Ballenrapers update(Ballenrapers entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Ballenrapers entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Ballenrapers find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Ballenrapers> findAll() {
        return BaseDAO.super.findAll();
    }
}
