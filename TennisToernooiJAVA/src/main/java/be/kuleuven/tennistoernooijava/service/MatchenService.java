package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalNumberException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalTimeException;
import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.WedstrijdleiderDAO;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.Uitslagen;

import java.util.*;

public class MatchenService {
    private final MatchenDAO matchenDAO;

    public MatchenService(MatchenDAO matchenDAO) {
        this.matchenDAO = matchenDAO;
    }

    public Matchen voegMatchAanToernooi(Toernooien toernooi, Velden veld,
                                Integer startDag, Integer startMaand, Integer startJaar,
                                Integer startdUur, Integer startMinuut
    ) {
        if (startDag<=0 || startDag >31 ){
            throw new IllegalDateException("Ongeldige begindag");
        }
        if (startMaand<=0 || startMaand >12 ){
            throw new IllegalDateException("Ongeldige beginmaand");
        }
        if (startJaar<=2023 ){
            throw new IllegalDateException("Ongeldig beginjaar");
        }
        if (startdUur<=0 || startdUur >24 ){
            throw new IllegalTimeException("Ongeldig Startuur");
        }
        if (startMinuut <0 || startMinuut >=60 ){
            throw new IllegalTimeException("Ongeldig Startuur");
        }

        Matchen match = new Matchen();
        Datums startDatum = new Datums();
        startDatum.setDag(startDag);
        startDatum.setMaand(startMaand);
        startDatum.setJaar(startJaar);
        startDatum.setUur(startdUur);
        startDatum.setMinuten(startMinuut);
        startDatum = new DatumsDAO().create(startDatum);
        match.setDatumID(startDatum);
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

    public Map<String, List<Integer>> calculateMatches(String aantalMatchen) {
        if(aantalMatchen.contains("[a-zA-Z]+")) {
            throw new IllegalNumberException("Dit is geen integer");
        }
        int aantal = Integer.parseInt(aantalMatchen);
        if(aantal%2 != 0) {
            throw new IllegalNumberException("Het aantal matchen moet een geheel getal zijn");
        }
        Map<String, List<Integer>> matchStages = new LinkedHashMap<>();
        List<Integer> startingMatches = new ArrayList<>();

        while(aantal > 4) {
            startingMatches.add(aantal);
            aantal /= 2;
        }

        matchStages.put("startingMatches", startingMatches);
        matchStages.put("semifinals", Collections.singletonList(2));
        matchStages.put("final", Collections.singletonList(1));

        return matchStages;

    }
}
