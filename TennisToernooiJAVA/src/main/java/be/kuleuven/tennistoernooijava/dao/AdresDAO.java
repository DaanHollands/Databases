package be.kuleuven.tennistoernooijava.dao;
import be.kuleuven.tennistoernooijava.model.Adres;
import java.util.List;

public class AdresDAO implements BaseDAO<Adres, Integer>{
    @Override
    public Class<Adres> getEntityClass() {
        return Adres.class;
    }

    @Override
    public Adres create(Adres entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Adres update(Adres entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Adres entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Adres find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Adres> findAll() {
        return BaseDAO.super.findAll();
    }
}
