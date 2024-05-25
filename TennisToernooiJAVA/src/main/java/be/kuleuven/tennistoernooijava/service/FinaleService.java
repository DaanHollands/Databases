package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FinaleService extends FinaleMatchenHelper {
    private static FinaleDAO finaleDAO;

    public FinaleService(FinaleDAO finaleDAO) {
        FinaleService.finaleDAO = finaleDAO;
    }

    public Finales voegFinaleAanToernooi(
            Toernooien toernooi, Velden veld,
            Integer startDag, Integer startMaand, Integer startJaar,
            String startUur, String startMinuut, Spelers scheids,
            ReeksenWaardes reeks, Integer reeksniveau, Integer ronde
    ) {

        Optional<RuntimeException> exception = validateExceptions(toernooi, veld, startDag, startMaand, startJaar, startUur, startMinuut);
        if(exception.isPresent()) {
            throw exception.get();
        }

        Finales finale = (Finales) createMatch(toernooi, veld, startDag, startMaand, startJaar, startUur, startMinuut, reeks, reeksniveau, ronde);
        Scheidsen nieuweScheids = new ScheidenDAO().find(scheids.getSpelerID());

        if(nieuweScheids == null) {
            nieuweScheids = new Scheidsen();
            nieuweScheids.setScheids(scheids);
            nieuweScheids.setArbiterRanking("Empty");
            nieuweScheids = new ScheidenDAO().create(nieuweScheids);
        }

        finale.setScheidsID(nieuweScheids);
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
                .filter(f -> f.getScorethus() != null)
                .max(Comparator.comparing(Finales::getScorethus))
                .orElse(null);
    }
}
