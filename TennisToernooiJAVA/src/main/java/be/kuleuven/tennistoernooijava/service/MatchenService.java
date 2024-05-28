package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.exceptions.*;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.Uitslagen;

import java.util.*;

public class MatchenService extends FinaleMatchenHelper {
    private final MatchenDAO matchenDAO;

    public MatchenService(MatchenDAO matchenDAO) {
        this.matchenDAO = matchenDAO;
    }

    public Matchen voegMatchAanToernooi(Toernooien toernooi, Velden veld,
                                Integer startDag, Integer startMaand, Integer startJaar,
                                String startUur, String startMinuut, Integer ronde,
                                ReeksenWaardes reeks, Integer reeksniveau
    ) {
        Optional<RuntimeException> exception = validateExceptions(toernooi, veld, startDag, startMaand, startJaar, startUur, startMinuut);
        if(exception.isPresent()) {
            throw exception.get();
        }
        Matchen match = matchenDAO.create(createMatch(true, toernooi, veld, startDag, startMaand, startJaar, startUur, startMinuut, reeks, reeksniveau, ronde));
        toernooi.addMatchen(match);
        return match;
    }


    public List<Matchen> getMatchesFrom(Spelers speler) {
        List<Matchen> matchen = new ArrayList<>(matchenDAO.getMatchenFrom(speler));
        return matchen;
    }

    public List<Matchen> getMatchesFromEverything(Spelers speler) {
        List<Matchen> matchen = new ArrayList<>(matchenDAO.getMatchenAndFinalesFrom(speler));
        return matchen;
    }



    public Matchen getHigestMatch(Spelers speler) {
        List<Matchen> match = this.getMatchesFrom(speler);
        return match.stream()
                .filter(m -> m.getScorethus() != null)
                .max(Comparator.comparing(Matchen::getScorethus))
                .orElse(null);
    }

    public void updateScores(Matchen match, String scoreThuis, String scoreUit, Uitslagen uitslagen) {
        if(scoreThuis == null || scoreThuis.isEmpty() || scoreThuis.matches(".*[a-zA-Z]+.*") || Integer.parseInt(scoreThuis) < 0) {
            throw new IllegalScoreException("De thuis score is geen geldige score");
        }
        if(scoreUit == null || scoreUit.isEmpty() || scoreUit.matches(".*[a-zA-Z]+.*") || Integer.parseInt(scoreUit) < 0) {
            throw new IllegalScoreException("De score uit is geen geldige score");
        }

        match.setScoreuit(Integer.parseInt(scoreUit));
        match.setScorethus(Integer.parseInt(scoreThuis));
        match.setUitslag(uitslagen);
        matchenDAO.update(match);
    }
}
