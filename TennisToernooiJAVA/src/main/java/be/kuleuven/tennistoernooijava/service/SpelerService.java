package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.SpelerDAO;
import be.kuleuven.tennistoernooijava.model.Spelers;

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
        Spelers speler = new Spelers();
        speler.setNaam(naam);
        speler.setGeslacht(geslacht);
        speler.setClub(2);
        speler.setGeboortedatum(datumService.createDatum(geboorteJaar, geboorteMaand, geboorteDag , 0,0).getDatumId());
        speler.setGewicht(gewicht);
        speler.setLengte(lengte);
        speler.setRanking(ranking);
    }
}
