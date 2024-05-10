package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.AdresDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.database.Tennisclubs;

import java.util.Set;

public class TennisclubService {
    private final TennisclubDAO tennisclubDAO;

    public TennisclubService(TennisclubDAO tennisclubDAO) {this.tennisclubDAO = tennisclubDAO;}

    public Tennisclubs create(String straatnaam, Integer straatnummer, Integer postcode, String naam){
        Tennisclubs tennisclub = new Tennisclubs();
        tennisclub.setAdresID(new AdresService(new AdresDAO()).getOrCreate(postcode, straatnaam, straatnummer));
        tennisclub.setNaam(naam);
        return tennisclubDAO.create(tennisclub);
    }
    
    public Set<Tennisclubs> getClubs() {
        return (Set<Tennisclubs>) tennisclubDAO.findAll();
    }
}
