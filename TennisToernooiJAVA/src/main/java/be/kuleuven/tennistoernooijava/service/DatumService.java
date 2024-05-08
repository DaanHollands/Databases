package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.DatumDAO;
import be.kuleuven.tennistoernooijava.model.Datums;

public class DatumService {

    private final DatumDAO datumDAO;

    public DatumService(DatumDAO datumDAO) {
        this.datumDAO = datumDAO;
    }

    public Datums createDatum(int jaar, int maand, int dag, int uur, int minuten) {
        Datums datum = new Datums();
        datum.setJaar(jaar);
        datum.setMaand(maand);
        datum.setDag(dag);
        datum.setUur(uur);
        datum.setMinuten(minuten);
        return datumDAO.create(datum);
    }
}