package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.EmptyInputException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalNumberException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalTimeException;
import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.ReeksenDAO;
import be.kuleuven.tennistoernooijava.dao.WedstrijdleiderDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
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
                                Integer startdUur, Integer startMinuut, Integer ronde,
                                Map.Entry<ReeksenWaardes, Integer> reeks
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
        if(veld == null) {
            throw new EmptyInputException("Het veld input mag niet leeg zijn!");
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
        match.setMatchRonde(ronde);
        Reeksen newReeks = new ReeksenService(new ReeksenDAO()).getReeks(reeks.getValue(), reeks.getKey());
        match.setReeks(newReeks);
        match = matchenDAO.create(match);
        toernooi.addMatchen(match);
        return match;
    }

    public List<Matchen> getMatchesFrom(Spelers speler) {
        List<Matchen> matchen = new ArrayList<>(matchenDAO.getMatchenFrom(speler));
        return matchen;
    }

    public List<Matchen> getMatchesFromEverything(Spelers speler) {
        List<Matchen> matchen = new ArrayList<>(matchenDAO.getMatchenFromEverything(speler));
        return matchen;
    }



    public Matchen getHigestMatch(Spelers speler) {
        List<Matchen> match = this.getMatchesFrom(speler);
        return match.stream()
                .filter(m -> m.getScorethus() != null)
                .max(Comparator.comparing(Matchen::getScorethus))
                .orElse(null);
    }

    public void updateScores(Matchen match, Integer scoreThuis, Integer scoreUit, Uitslagen uitslagen) {
        match.setScoreuit(scoreUit);
        match.setScorethus(scoreThuis);
        match.setUitslag(uitslagen);
        matchenDAO.update(match);
    }

    public Map<Integer, List<Integer>> calculateMatches(Integer aantalMatchen) {
        int aantal = aantalMatchen;

        if(aantal%2 != 0) {
            throw new IllegalNumberException("Het aantal matchen moet een geheel getal zijn");
        }
        Map<Integer, List<Integer>> matchStages = new LinkedHashMap<>();
        int round = 1;

        while(aantal >= 1) {
            matchStages.put(round, Collections.singletonList(aantal));
            aantal /= 2;
            round++;
        }

        return matchStages;
    }
}
