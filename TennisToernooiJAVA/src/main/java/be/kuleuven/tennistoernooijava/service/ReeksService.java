package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.ReeksDAO;
import be.kuleuven.tennistoernooijava.model.Reeks;

public class ReeksService {
    private final ReeksDAO reeksDAO;

    public ReeksService(ReeksDAO reeksDAO) {this.reeksDAO = reeksDAO;}

    public Reeks create(){
        return reeksDAO.create(new Reeks());
    }
}
