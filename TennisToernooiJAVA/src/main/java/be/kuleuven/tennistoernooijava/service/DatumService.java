package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.model.Datums;

import java.util.List;

public class DatumService {

    private final DatumsDAO datumsDAO;

    public DatumService(DatumsDAO datumsDAO) {
        this.datumsDAO = datumsDAO;
    }

    public Datums createDatum(int jaar, int maand, int dag, int uur, int minuten) {
        Datums datum = new Datums();
        datum.setJaar(jaar);
        datum.setMaand(maand);
        datum.setDag(dag);
        datum.setUur(uur);
        datum.setMinuten(minuten);
        return datumsDAO.create(datum);
    }
}