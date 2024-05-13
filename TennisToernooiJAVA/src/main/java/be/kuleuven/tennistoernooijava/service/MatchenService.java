package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.WedstrijdleiderDAO;
import be.kuleuven.tennistoernooijava.database.*;

public class MatchenService {
    private final MatchenDAO matchenDAO;

    public MatchenService(MatchenDAO matchenDAO) {
        this.matchenDAO = matchenDAO;
    }

    public Matchen voegMatchAanToernooi(Toernooien toernooi, Velden veld,
                                Integer startDag, Integer startMaand, Integer startJaar,
                                Integer startdUur, Integer startMinuut,
                                Spelers speler
    ) {
        Matchen match = new Matchen();
        Datums startDatum = new Datums();
        startDatum.setDag(startDag);
        startDatum.setMaand(startMaand);
        startDatum.setJaar(startJaar);
        startDatum.setUur(startdUur);
        startDatum.setMinuten(startMinuut);
        match.setDatumID(startDatum);
        Wedstrijdleider wedstrijdleider = new Wedstrijdleider();
        wedstrijdleider.setSpeler(speler);
        wedstrijdleider = new WedstrijdleiderDAO().create(wedstrijdleider);
        match.setWedstrijdleider(wedstrijdleider);

        match.setToernooiID(toernooi);
        match.setVeldID(veld);
        return matchenDAO.create(match);
    }
}
