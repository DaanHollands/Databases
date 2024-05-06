package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.SpelerDAO;
import be.kuleuven.tennistoernooijava.model.Spelers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;

public class SpelerService {
    private final SpelerDAO spelerDAO;
    private final DatumService datumService;

    public SpelerService(SpelerDAO spelerDAO, DatumService datumService) {
        this.spelerDAO = spelerDAO;
        this.datumService = datumService;
    }

    public void maakSpeler(
            String naam,
            String telefoonNummer,
            String email,
            String geslacht,
            BigInteger gewicht,
            BigInteger lengte,
            int geboorteDag,
            int geboorteMaand,
            int geboorteJaar,
            int ranking
    ) {
        Spelers spelers = new Spelers();
        spelers.setNaam(naam);
        spelers.setGeslacht(geslacht);
        spelers.setClub(2);
        spelers.setGeboortedatum(datumService.createDatum(geboorteJaar, geboorteMaand, geboorteDag , 0,0).getDatumId());
        spelers.setGewicht(gewicht);
        spelers.setLengte(lengte);
        spelers.setRanking(ranking);
    }
}
