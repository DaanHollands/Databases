package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.NiveauReeksDAO;
import be.kuleuven.tennistoernooijava.model.NiveauReeks;

public class NiveauReeksService {
    private NiveauReeksDAO niveauReeksDAO;

    public NiveauReeksService(NiveauReeksDAO niveauReeksDAO) {this.niveauReeksDAO = niveauReeksDAO;}

    public NiveauReeks create(String niveau, int reeksID){
        NiveauReeks niveauReeks = new NiveauReeks();
        niveauReeks.setNiveau(niveau);
        niveauReeks.setReeksId(reeksID);
        return niveauReeksDAO.create(niveauReeks);
    }

}
