package be.kuleuven.tennistoernooijava.dao;

import java.util.List;
import be.kuleuven.tennistoernooijava.model.Deelnamen;
public class DeelnameDAO implements BaseDAO<Deelnamen, Integer>{
    @Override
    public Class<Deelnamen> getEntityClass() {
        return Deelnamen.class;
    }


}
