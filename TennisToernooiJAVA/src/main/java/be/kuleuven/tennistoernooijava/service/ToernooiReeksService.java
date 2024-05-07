package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.ToernooiReeksDAO;
import be.kuleuven.tennistoernooijava.model.ToernooiReeks;

public class ToernooiReeksService {
    private final ToernooiReeksDAO toernooiReeksDAO;

    public ToernooiReeksService(ToernooiReeksDAO toernooiReeksDAO) {this.toernooiReeksDAO = toernooiReeksDAO;}

    public ToernooiReeks create(int reeksID, int ToernooiID){
        ToernooiReeks toernooiReeks = new ToernooiReeks();
        toernooiReeks.setReeksId(reeksID);
        toernooiReeks.setToernooiId(ToernooiID);
        return toernooiReeksDAO.create(toernooiReeks);
    }

}
