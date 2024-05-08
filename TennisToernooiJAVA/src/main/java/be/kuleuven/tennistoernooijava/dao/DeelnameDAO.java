package be.kuleuven.tennistoernooijava.dao;

import java.util.List;
import be.kuleuven.tennistoernooijava.model.Deelnamen;
public class DeelnameDAO implements BaseDAO<Deelnamen, Integer>{
    @Override
    public Class<Deelnamen> getEntityClass() {
        return Deelnamen.class;
    }

    @Override
    public Deelnamen create(Deelnamen entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Deelnamen update(Deelnamen entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Deelnamen entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Deelnamen find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Deelnamen> findAll() {
        return BaseDAO.super.findAll();
    }
}
