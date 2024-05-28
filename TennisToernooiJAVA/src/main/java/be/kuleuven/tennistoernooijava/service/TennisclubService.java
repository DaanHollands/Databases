package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.exceptions.*;
import be.kuleuven.tennistoernooijava.dao.AdresDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.dao.VeldenDAO;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TennisclubService {
    private final TennisclubDAO tennisclubDAO;

    public TennisclubService(TennisclubDAO tennisclubDAO) {this.tennisclubDAO = tennisclubDAO;}

    public Tennisclubs create(Spelers speler, String straatnaam, String straatnummer, String postcode, String naam) {

        Optional<RuntimeException> exception = checkForCreateException(speler, straatnaam, straatnummer, postcode, naam);
        if(exception.isPresent()) {
            throw exception.get();
        }

        Tennisclubs tennisclub = new Tennisclubs();
        Adressen adres = new AdresService(new AdresDAO()).getOrCreate(Integer.parseInt(postcode),straatnaam, Integer.parseInt(straatnummer));
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

    public Tennisclubs addSpelerToClub(Spelers speler, String tennisclubnaam) {
        if(speler.getTennisclubID() != null) {
            throw new IllegalClubException("Je zit al bij een club, je moet deze club eerst verlaten!");
        }
        if(!this.getClubNames().contains(tennisclubnaam)) {
            throw new ClubNotFoundException("De tennisclub die u wilt toe behoren bestaat niet");
        }
        Tennisclubs club = this.getClubFromName(tennisclubnaam);
        club.addSpeler(speler);
        speler.setTennisclubID(club);
        return tennisclubDAO.update(club);
    }

    public void leaveClub(Spelers speler, Tennisclubs club) {
        speler.setTennisclubID(null);
        club.getSpelers().remove(speler);
        tennisclubDAO.update(club);
    }

    public Toernooien getToernooiVanSpeler(Tennisclubs club, Spelers speler) {
        Set<Toernooien> toernooien = club.getToernooien();
        return toernooien.stream()
            .filter(a -> a.getWedstrijdleider().getSpeler().equals(speler)).findFirst()
            .orElse(null);
    }

    public Set<Toernooien> getToernooien(Tennisclubs club) {
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
            Velden newVeld = new Velden();
            newVeld.setVeldsoort(veldSoort);
            newVeld.setTennisclubID(club);
            newVeld = new VeldenDAO().create(newVeld);
            club.addVeld(newVeld);
            return newVeld;

        }
        return null;
    }

    public Set<Spelers> getAlleSpelers(Tennisclubs club) {
        return club.getSpelers();
    }

    private boolean onlyLetters(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private Optional<RuntimeException> checkForCreateException(Spelers speler, String straatnaam, String straatnummer, String postcode, String clubNaam)
    {
        if(speler.getTennisclubID() != null) {
            return Optional.of(new IllegalClubException("Je zit al bij een club, je moet deze club eerst verlaten!"));
        }
        if(clubNaam.isEmpty()) {
            return Optional.of(new EmptyInputException("De clubnaam is leeg!"));
        }
        if(postcode == null || postcode.isEmpty() || postcode.matches(".*[a-zA-Z]+.*") || Integer.parseInt(postcode) <= 0 || Integer.parseInt(postcode) >= 9999) {
            return Optional.of(new IllegalAdresException("Ongeldige postcode!"));
        }
        if(straatnaam == null || !onlyLetters(straatnaam)) {
            return Optional.of(new IllegalAdresException("Ongeldige straatnaam!"));
        }
        if(straatnummer == null || straatnummer.isEmpty() || straatnummer.matches(".*[a-zA-Z]+.*") || Integer.parseInt(postcode) <= 0) {
            return Optional.of(new IllegalAdresException("Ongeldige straatnummer!"));
        }

        return Optional.empty();
    }
}
