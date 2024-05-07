package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Supporterde;

import java.util.List;

public class SupporterdeDAO implements BaseDAO<Supporterde, Integer> {
    @Override
    public Class<Supporterde> getEntityClass() {
        return Supporterde.class;
    }

    @Override
    public Supporterde create(Supporterde entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Supporterde update(Supporterde entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Supporterde entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Supporterde find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Supporterde> findAll() {
        return BaseDAO.super.findAll();
    }
}
