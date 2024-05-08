package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.AdresDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.model.Adressen;
import be.kuleuven.tennistoernooijava.model.Tennisclubs;

public class TennisclubService {
    private final TennisclubDAO tennisclubDAO;

    public TennisclubService(TennisclubDAO tennisclubDAO) {this.tennisclubDAO = tennisclubDAO;}

    public Tennisclubs create(String straatnaam, Integer straatnummer, Integer postcode, String naam){
        Tennisclubs tennisclub = new Tennisclubs();
        tennisclub.setAdres(new AdresService(new AdresDAO()).getOrCreate(postcode, straatnaam, straatnummer));
        tennisclub.setNaam(naam);
        return tennisclubDAO.create(tennisclub);
    }
}
