package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.SupporterDAO;
import be.kuleuven.tennistoernooijava.model.Supporters;

public class SupporterService {
    private final SupporterDAO supporterDAO;

    public SupporterService(SupporterDAO supporterDAO) {this.supporterDAO = supporterDAO;}

    public Supporters create(int spelerID, int clubID){
        Supporters supporter = new Supporters();
        supporter.setClubId(clubID);
        supporter.setSpelerId(spelerID);
        return supporterDAO.create(supporter);
    }
}
