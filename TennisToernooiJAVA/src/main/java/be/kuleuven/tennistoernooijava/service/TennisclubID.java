package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.model.Tennisclub;

public class TennisclubID {
    private final TennisclubDAO tennisclubDAO;

    public TennisclubID(TennisclubDAO tennisclubDAO) {this.tennisclubDAO = tennisclubDAO;}

    public Tennisclub create(int adresID, String naam){
        Tennisclub tennisclub = new Tennisclub();
        tennisclub.setAdres(adresID);
        tennisclub.setNaam(naam);
        return tennisclubDAO.create(tennisclub);
    }
}
