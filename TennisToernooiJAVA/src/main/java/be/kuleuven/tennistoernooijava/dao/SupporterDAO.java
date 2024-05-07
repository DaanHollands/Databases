package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Supporter;

import java.util.List;

public class SupporterDAO implements BaseDAO<Supporter, Integer> {
    @Override
    public Class<Supporter> getEntityClass() {
        return Supporter.class;
    }

    @Override
    public Supporter create(Supporter entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Supporter update(Supporter entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Supporter entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Supporter find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Supporter> findAll() {
        return BaseDAO.super.findAll();
    }
}
