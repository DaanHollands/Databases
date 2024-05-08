package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Supporters;

import java.util.List;

public class SupporterDAO implements BaseDAO<Supporters, Integer> {
    @Override
    public Class<Supporters> getEntityClass() {
        return Supporters.class;
    }

    @Override
    public Supporters create(Supporters entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Supporters update(Supporters entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Supporters entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Supporters find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Supporters> findAll() {
        return BaseDAO.super.findAll();
    }
}
