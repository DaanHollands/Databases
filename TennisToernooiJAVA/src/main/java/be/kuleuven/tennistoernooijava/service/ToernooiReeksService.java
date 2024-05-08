package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.ToernooiReeksDAO;
import be.kuleuven.tennistoernooijava.model.ToernooiReeksen;

public class ToernooiReeksService {
    private final ToernooiReeksDAO toernooiReeksDAO;

    public ToernooiReeksService(ToernooiReeksDAO toernooiReeksDAO) {this.toernooiReeksDAO = toernooiReeksDAO;}

    public ToernooiReeksen create(int reeksID, int ToernooiID){
        ToernooiReeksen toernooiReeks = new ToernooiReeksen();
        toernooiReeks.setReeksId(reeksID);
        toernooiReeks.setToernooiId(ToernooiID);
        return toernooiReeksDAO.create(toernooiReeks);
    }

}
