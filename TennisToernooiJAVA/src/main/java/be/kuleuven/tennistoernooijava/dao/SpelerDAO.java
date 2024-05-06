package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Spelers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SpelerDAO implements BaseDAO<Spelers, Integer> {

    @Override
    public Class<Spelers> getEntityClass() {
        return Spelers.class;
    }

    @Override
    public Spelers create(Spelers entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Spelers update(Spelers entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Spelers entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Spelers find(Integer id) {
        return BaseDAO.super.find(id);
    }

    @Override
    public List<Spelers> findAll() {
        return BaseDAO.super.findAll();
    }
}
