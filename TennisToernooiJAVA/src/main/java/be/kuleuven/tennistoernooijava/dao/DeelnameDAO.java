package be.kuleuven.tennistoernooijava.dao;

import java.util.List;
import be.kuleuven.tennistoernooijava.model.Deelname;
public class DeelnameDAO implements BaseDAO<Deelname, Integer>{
    @Override
    public Class<Deelname> getEntityClass() {
        return Deelname.class;
    }

    @Override
    public Deelname create(Deelname entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Deelname update(Deelname entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Deelname entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Deelname find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Deelname> findAll() {
        return BaseDAO.super.findAll();
    }
}
