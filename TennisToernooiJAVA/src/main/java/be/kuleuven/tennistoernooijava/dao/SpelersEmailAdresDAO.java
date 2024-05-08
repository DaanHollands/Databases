package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.SpelersEmailadressen;

import java.util.List;

public class SpelersEmailAdresDAO implements BaseDAO<SpelersEmailadressen, Integer> {
    @Override
    public Class<SpelersEmailadressen> getEntityClass() {
        return SpelersEmailadressen.class;
    }

    @Override
    public SpelersEmailadressen create(SpelersEmailadressen entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public SpelersEmailadressen update(SpelersEmailadressen entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(SpelersEmailadressen entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public SpelersEmailadressen find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<SpelersEmailadressen> findAll() {
        return BaseDAO.super.findAll();
    }
}
