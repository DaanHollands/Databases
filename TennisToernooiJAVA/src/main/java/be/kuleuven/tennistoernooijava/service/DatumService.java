package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.DatumDAO;
import be.kuleuven.tennistoernooijava.model.Datum;

public class DatumService {

    private final DatumDAO datumDAO;

    public DatumService(DatumDAO datumDAO) {
        this.datumDAO = datumDAO;
    }

    public Datum createDatum(int jaar, int maand, int dag, int uur, int minuten) {
        Datum datum = new Datum();
        datum.setJaar(jaar);
        datum.setMaand(maand);
        datum.setDag(dag);
        datum.setUur(uur);
        datum.setMinuten(minuten);
        return datumDAO.create(datum);
    }
}