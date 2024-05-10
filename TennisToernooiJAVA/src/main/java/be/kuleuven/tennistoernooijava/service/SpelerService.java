package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalSpelerRequest;
import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.SpelerEmailadressenDAO;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.database.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.database.Datums;
import be.kuleuven.tennistoernooijava.database.Spelers;
import be.kuleuven.tennistoernooijava.models.SpelerSessie;

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

    public void removeEmailFromSpeler(Integer spelerID, String email) {
        SpelerEmailadressenDAO emailadressenDAO = new SpelerEmailadressenDAO();
        Set<SpelerEmailadressen> emailadressen = spelersDAO.find(spelerID).getEmails();
        for (SpelerEmailadressen spelerEmailadres : emailadressen) {
            if(spelerEmailadres.getEmail().equals(email)) {
                emailadressenDAO.delete(spelerEmailadres);
//                SpelerSessie.getSessie().getSpeler().removeEmailAdres(spelerEmailadres);
                return;
            }
        }
    }
}
