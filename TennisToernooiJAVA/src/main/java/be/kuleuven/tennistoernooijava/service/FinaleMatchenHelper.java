package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.exceptions.EmptyInputException;
import be.kuleuven.tennistoernooijava.exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.exceptions.IllegalTimeException;
import be.kuleuven.tennistoernooijava.exceptions.InvalidInputException;
import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.ReeksenDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;

import java.util.*;

public abstract class FinaleMatchenHelper {

    protected Matchen createMatch(
            boolean isMatch,
            Toernooien toernooi, Velden veld,
            Integer startDag, Integer startMaand, Integer startJaar,
            String startUur, String startMinuut,
            ReeksenWaardes reeks, Integer reeksniveau, Integer ronde
    )
    {
        Matchen match = null;
        if(isMatch) {
            match = new Matchen();
        }else {
            match = new Finales();
        }
        Datums startDatum = new Datums();
        startDatum.setDag(startDag);
        startDatum.setMaand(startMaand);
        startDatum.setJaar(startJaar);
        startDatum.setUur(Integer.parseInt(startUur));
        startDatum.setMinuten(Integer.parseInt(startMinuut));
        startDatum = new DatumsDAO().create(startDatum);
        match.setDatumID(startDatum);
        match.setToernooiID(toernooi);
        match.setVeldID(veld);
        match.setMatchRonde(ronde);
        Reeksen newReeks = new ReeksenService(new ReeksenDAO()).getReeks(reeksniveau, reeks);
        match.setReeks(newReeks);
        return match;
    }

    protected Optional<RuntimeException> validateExceptions(
            Toernooien toernooi, Velden veld,
            Integer startDag, Integer startMaand, Integer startJaar,
            String startdUur, String startMinuut
    )
    {
        if (startDag <= 0 || startDag >31 ){
            return Optional.of(new IllegalDateException("Ongeldige begindag"));
        }
        if (startMaand<=0 || startMaand >12 ){
            return Optional.of(new IllegalDateException("Ongeldige beginmaand"));
        }
        if (startJaar<=2023 ){
            return Optional.of(new IllegalDateException("Ongeldige beginjaar"));
        }
        if (startdUur == null || startdUur.isEmpty() || startdUur.matches(".*[a-zA-Z]+.*") || Integer.parseInt(startdUur) < 0 || Integer.parseInt(startdUur) >= 24 ) {
            return Optional.of(new IllegalTimeException("Ongeldig Startuur"));
        }
        if (startMinuut == null || startMinuut.isEmpty() || startMinuut.matches(".*[a-zA-Z]+.*") || Integer.parseInt(startMinuut) < 0 || Integer.parseInt(startMinuut) >= 60) {
            return Optional.of(new IllegalTimeException("Ongeldig Startuur"));
        }
        if(veld == null) {
            throw new EmptyInputException("Het veld input mag niet leeg zijn!");
        }
        return matchInToernooi(toernooi, startDag, startMaand, startJaar);
    }

    private Optional<RuntimeException> matchInToernooi(Toernooien toernooi, Integer startDag, Integer startMaand, Integer startJaar){
        Datums toernooiBeginDatum = toernooi.getBeginDatumID();
        Datums toenooiEindDatum = toernooi.getEindDatumID();

        if (startJaar < toernooiBeginDatum.getJaar() || startJaar > toenooiEindDatum.getJaar()) {
            return Optional.of(new IllegalDateException("Ongeldig jaar, het jaar tussen het toernooi liggen"));
        }
        if (startMaand < toernooiBeginDatum.getMaand() || startMaand > toenooiEindDatum.getMaand()){
            return Optional.of(new IllegalDateException("Ongeldige maand, de maand moet tussen het toernooi liggen"));
        }
        if (startDag < toernooiBeginDatum.getDag() || startDag > toenooiEindDatum.getDag()){
            return Optional.of(new IllegalDateException("Ongeldige dag, de dag moet tussen het toernooi liggen"));
        }
        return Optional.empty();
    }

    public static Map<Integer, List<Integer>> calculateMatches(Integer aantalMatchen) {
        int aantal = aantalMatchen;

        if(aantal%2 != 0) {
            throw new InvalidInputException("Het aantal matchen moet een geheel getal zijn");
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
