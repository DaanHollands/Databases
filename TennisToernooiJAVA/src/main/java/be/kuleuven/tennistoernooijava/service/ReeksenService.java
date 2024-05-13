package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.ReeksenDAO;
import be.kuleuven.tennistoernooijava.database.Reeksen;

public class ReeksenService {
    private final ReeksenDAO reeksenDAO;

    public ReeksenService(ReeksenDAO reeksenDAO) {
        this.reeksenDAO = reeksenDAO;
    }

    public Reeksen getReeks(String reeksNiveau) {
        Reeksen reeks = reeksenDAO.findFromNivea(reeksNiveau);
        if(reeks != null) {
            return reeks;
        }
        Reeksen newReeks = new Reeksen();
        newReeks.setNiveau(reeksNiveau);
        newReeks = reeksenDAO.create(newReeks);
        return newReeks;
    }
}
