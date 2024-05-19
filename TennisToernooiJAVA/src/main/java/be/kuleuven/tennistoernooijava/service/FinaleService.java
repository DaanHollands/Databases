package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalTimeException;
import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FinaleService {
    private static FinaleDAO finaleDAO;

    public FinaleService(FinaleDAO finaleDAO) {
        this.finaleDAO = finaleDAO;
    }

    public Finales voegFinaleAanToernooi(Toernooien toernooi, Velden veld,
                                      Integer startDag, Integer startMaand, Integer startJaar,
                                      Integer startdUur, Integer startMinuut, Spelers scheids,
                                     Map.Entry<ReeksenWaardes, Integer> reeks
    ) {
        Finales finale = new Finales();
        Datums startDatum = new Datums();
        startDatum.setDag(startDag);
        startDatum.setMaand(startMaand);
        startDatum.setJaar(startJaar);
        startDatum.setUur(startdUur);
        startDatum.setMinuten(startMinuut);
        startDatum = new DatumsDAO().create(startDatum);
        finale.setDatumID(startDatum);
        finale.setIsFirstMatch(false);
        Scheidsen nieuweScheids = new ScheidenDAO().find(scheids.getSpelerID());

        if(nieuweScheids == null) {
            nieuweScheids = new Scheidsen();
            nieuweScheids.setScheids(scheids);
            nieuweScheids.setArbiterRanking("Was dees");
            nieuweScheids = new ScheidenDAO().create(nieuweScheids);
        }
        finale.setScheidsID(nieuweScheids);
        finale.setToernooiID(toernooi);
        finale.setVeldID(veld);
        Reeksen newReeks = new ReeksenService(new ReeksenDAO()).getReeks(reeks.getValue(), reeks.getKey());
        finale.setReeks(newReeks);
        finale = finaleDAO.create(finale);
        toernooi.addMatchen(finale);
        return finale;
    }

    public List<Finales> getFinalesFrom(Spelers speler) {
        return finaleDAO.getFinalesFromSpeler(speler);
    }

    public Finales getHighestFinal(Spelers speler) {
        List<Finales> finales = this.getFinalesFrom(speler);
        return finales.stream()
                .max(Comparator.comparing(Finales::getScorethus))
                .orElse(null);
    }
}
