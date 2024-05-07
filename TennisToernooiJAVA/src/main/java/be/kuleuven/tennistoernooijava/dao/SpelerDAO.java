package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Speler;

import java.util.List;

public class SpelerDAO implements BaseDAO<Speler, Integer> {

    @Override
    public Class<Speler> getEntityClass() {
        return Speler.class;
    }

    @Override
    public Speler create(Speler entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Speler update(Speler entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Speler entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Speler find(Integer id) {
        return BaseDAO.super.find(id);
    }

    @Override
    public List<Speler> findAll() {
        return BaseDAO.super.findAll();
    }
}
