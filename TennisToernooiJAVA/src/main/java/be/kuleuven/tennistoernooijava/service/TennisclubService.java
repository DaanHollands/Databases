package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.AdresDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.dao.VeldenDAO;
import be.kuleuven.tennistoernooijava.database.*;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TennisclubService {
    private final TennisclubDAO tennisclubDAO;

    public TennisclubService(TennisclubDAO tennisclubDAO) {this.tennisclubDAO = tennisclubDAO;}

    public Tennisclubs create(Spelers speler, String straatnaam, Integer straatnummer, Integer postcode, String naam){
        Tennisclubs tennisclub = new Tennisclubs();
        Adressen adres = new AdresService(new AdresDAO()).getOrCreate(postcode,straatnaam,straatnummer);
        tennisclub.setAdresID(adres);
        tennisclub.setNaam(naam);
        tennisclub = tennisclubDAO.create(tennisclub);
        tennisclub.addSpeler(speler);
        return tennisclub;
    }
    
    public List<String> getClubNames() {
        List<String> lijst = new ArrayList<>();

        tennisclubDAO.findAll().forEach(e -> {
            lijst.add(e.getNaam());
        });
        return lijst;
    }

    public Tennisclubs getClubFromName(String clubName) {
        for (Tennisclubs tennisclub : tennisclubDAO.findAll()) {
            if(tennisclub.getNaam().equals(clubName)) {
               return tennisclub;
            }
        }
        return null;
    }

    public Tennisclubs addSpelerToClub(Spelers speler, Tennisclubs club) {
        tennisclubDAO.find(club.getClubID()).addSpeler(speler);
        speler.setTennisclubID(club);
        return tennisclubDAO.update(club);
    }

    public Set<Toernooien> getToernooien(Tennisclubs club) {
        club = tennisclubDAO.find(club.getClubID());
        return club.getToernooien();
    }

    public Velden getOrCreateVeld(VeldSoort veldSoort, Tennisclubs club) {
        club = tennisclubDAO.find(club.getClubID());
        if(club != null) {
            for (Velden velden : club.getVelden()) {
                if(velden.getVeldsoort() == veldSoort) {
                    return velden;
                }
            }
            VeldenDAO veldenDAO = new VeldenDAO();
            Velden newVeld = new Velden();
            newVeld.setVeldsoort(veldSoort);
            newVeld = veldenDAO.create(newVeld);
            club.addVeld(newVeld);
            return newVeld;

        }
        return null;
    }

    public Set<Spelers> getAlleSpelers(Tennisclubs club) {
        Tennisclubs gevondenClub = tennisclubDAO.find(club.getClubID());
        return gevondenClub.getSpelers();
    }


}
