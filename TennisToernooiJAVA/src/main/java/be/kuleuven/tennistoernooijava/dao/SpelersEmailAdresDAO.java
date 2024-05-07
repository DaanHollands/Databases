package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.SpelersEmailadres;

import java.util.List;

public class SpelersEmailAdresDAO implements BaseDAO<SpelersEmailadres, Integer> {
    @Override
    public Class<SpelersEmailadres> getEntityClass() {
        return SpelersEmailadres.class;
    }

    @Override
    public SpelersEmailadres create(SpelersEmailadres entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public SpelersEmailadres update(SpelersEmailadres entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(SpelersEmailadres entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public SpelersEmailadres find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<SpelersEmailadres> findAll() {
        return BaseDAO.super.findAll();
    }
}
