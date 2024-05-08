package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.ReeksDAO;
import be.kuleuven.tennistoernooijava.model.Reeksen;

public class ReeksService {
    private final ReeksDAO reeksDAO;

    public ReeksService(ReeksDAO reeksDAO) {this.reeksDAO = reeksDAO;}

    public Reeksen create(){
        return reeksDAO.create(new Reeksen());
    }
}
