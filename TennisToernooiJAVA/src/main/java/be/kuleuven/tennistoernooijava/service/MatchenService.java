package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.WedstrijdleiderDAO;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.Uitslagen;

import java.util.Comparator;
import java.util.List;

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
        startDatum = new DatumsDAO().create(startDatum);
        match.setDatumID(startDatum);
        Wedstrijdleider wedstrijdleider = new Wedstrijdleider();
        wedstrijdleider.setSpeler(speler);
        wedstrijdleider = new WedstrijdleiderDAO().create(wedstrijdleider);
        match.setWedstrijdleider(wedstrijdleider);

        match.setToernooiID(toernooi);
        match.setVeldID(veld);
        match = matchenDAO.create(match);
        toernooi.addMatchen(match);
        return match;
    }

    public List<Matchen> getMatchesFrom(Spelers speler) {
        return matchenDAO.getMatchenFrom(speler);
    }

    public Matchen getHigestMatch(Spelers speler) {
        List<Matchen> matchen = this.getMatchesFrom(speler);
        return matchen.stream()
                .max(Comparator.comparing(Matchen::getScorethus))
                .orElse(null);
    }

    public void updateScores(Matchen match, Integer scoreThuis, Integer scoreUit, Uitslagen uitslagen) {
        match.setScoreuit(scoreUit);
        match.setScorethus(scoreThuis);
        match.setUitslag(uitslagen);
        matchenDAO.update(match);
    }
}
