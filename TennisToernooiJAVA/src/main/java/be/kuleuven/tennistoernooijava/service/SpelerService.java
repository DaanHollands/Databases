package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalSpelerRequest;
import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.SpelerEmailadressenDAO;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.models.Datums;
import be.kuleuven.tennistoernooijava.models.Spelers;

import java.util.Set;

public class SpelerService {
    private final SpelersDAO spelersDAO;

    public SpelerService(SpelersDAO spelersDAO) {
        this.spelersDAO = spelersDAO;
    }

    public Spelers createSpeler(String naam, String telefoonnummer,
                                Integer geboorteDag, Integer geboorteMaand, Integer geboorteJaar,
                                Integer gewicht, Integer lengte, Integer ranking, Geslachten geslacht,
                                String email) {
        Spelers speler = new Spelers();
        speler.setNaam(naam);
        speler.setTelefoonnummer(telefoonnummer);
        speler.setGewicht(gewicht);
        Datums geboorteDatums = new Datums();
        geboorteDatums.setDag(geboorteDag);
        geboorteDatums.setJaar(geboorteJaar);
        geboorteDatums.setMaand(geboorteMaand);
        speler.setDatumID(new DatumsDAO().create(geboorteDatums));
        speler.setGeslacht(geslacht);
        speler.setRanking(ranking);
        speler.setLengte(lengte);
        speler.setTennisclubID(null);

        speler = spelersDAO.create(speler);

        SpelerEmailadressen spelerEmailadressen = new SpelerEmailadressen();
        spelerEmailadressen.setEmail(email);
        speler.addEmails(spelerEmailadressen);
        spelerEmailadressen.setSpelerID(speler);
        SpelerEmailadressenDAO emailadressenDAO = new SpelerEmailadressenDAO();
        emailadressenDAO.create(spelerEmailadressen);

        return speler;
    }

    public Spelers getSpeler(Integer spelerID) {
        Spelers speler = spelersDAO.find(spelerID);
        if(speler == null) {
            throw new IllegalSpelerRequest("Speler bestaat nog niet");
        }
        return speler;
    }

    public void joinClub(Tennisclubs club, Spelers speler) {
        speler.setTennisclubID(club);
        spelersDAO.update(speler);
    }

    public void removeEmailFromSpeler(Integer spelerID, String email) {
        SpelerEmailadressenDAO emailadressenDAO = new SpelerEmailadressenDAO();
        Set<SpelerEmailadressen> emailadressen = spelersDAO.find(spelerID).getEmails();
        for (SpelerEmailadressen spelerEmailadres : emailadressen) {
            if(spelerEmailadres.getEmail().equals(email)) {
                SpelerSessie.getSessie().getSpeler().removeEmailAdres(spelerEmailadres);
                spelersDAO.update(SpelerSessie.getSessie().getSpeler());
                return;
            }
        }
    }

    public void leaveClub(Integer spelerID, Tennisclubs club) {
        Spelers gevondenSpeler = spelersDAO.find(spelerID);
        if(gevondenSpeler.getTennisclubID().getClubID().equals(club.getClubID())) {
            gevondenSpeler.setTennisclubID(null);
            spelersDAO.update(gevondenSpeler);
        }
        else {
            throw new IllegalArgumentException("Er is een probleem om de huidige club te verwijderen");
        }
    }
}
