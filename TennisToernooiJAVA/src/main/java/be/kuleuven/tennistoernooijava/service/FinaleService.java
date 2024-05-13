package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.dao.ScheidenDAO;
import be.kuleuven.tennistoernooijava.dao.WedstrijdleiderDAO;
import be.kuleuven.tennistoernooijava.database.*;

public class FinaleService {
    private static FinaleDAO finaleDAO;

    public FinaleService(FinaleDAO finaleDAO) {
        this.finaleDAO = finaleDAO;
    }

    public Finales voegFinaleAanToernooi(Toernooien toernooi, Velden veld,
                                      Integer startDag, Integer startMaand, Integer startJaar,
                                      Integer startdUur, Integer startMinuut,
                                      Spelers wedstrijdleider, Spelers scheids
    ) {
        Finales finale = new Finales();
        Datums startDatum = new Datums();
        startDatum.setDag(startDag);
        startDatum.setMaand(startMaand);
        startDatum.setJaar(startJaar);
        startDatum.setUur(startdUur);
        startDatum.setMinuten(startMinuut);
        finale.setDatumID(startDatum);
        Wedstrijdleider nieuweWedstrijdleider = new Wedstrijdleider();
        nieuweWedstrijdleider.setSpeler(wedstrijdleider);
        nieuweWedstrijdleider = new WedstrijdleiderDAO().create(nieuweWedstrijdleider);
        finale.setWedstrijdleider(nieuweWedstrijdleider);
        Scheidsen nieuweScheids = new Scheidsen();
        nieuweScheids.setScheids(scheids);
        nieuweScheids = new ScheidenDAO().create(nieuweScheids);
        finale.setScheidsID(nieuweScheids);
        finale.setToernooiID(toernooi);
        finale.setVeldID(veld);
        return finaleDAO.create(finale);
    }
}
