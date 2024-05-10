package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.database.*;

public class MatchenService {
    private final MatchenDAO matchenDAO;

    public MatchenService(MatchenDAO matchenDAO) {
        this.matchenDAO = matchenDAO;
    }

    public void voegMatchAanToernooi(Toernooien toernooi, Velden veld,
                                Integer startDag, Integer startMaand, Integer startJaar,
                                Integer startdUur, Integer startMinuut,
                                Integer scoretUit, Integer scoreThuis,
                                Wedstrijdleider wedstrijdleider
    ) {
        Matchen match = new Matchen();
        Datums startDatum = new Datums();
        startDatum.setDag(startDag);
        startDatum.setMaand(startMaand);
        startDatum.setJaar(startJaar);
        startDatum.setUur(startdUur);
        startDatum.setMinuten(startMinuut);
        match.setDatumID(startDatum);

        match.setScorethus(scoreThuis);
        match.setScoreuit(scoretUit);
        match.setWedstrijdleider(wedstrijdleider);

        match.setToernooiID(toernooi);
        match.setVeldID(veld);
        matchenDAO.create(match);
    }

}
