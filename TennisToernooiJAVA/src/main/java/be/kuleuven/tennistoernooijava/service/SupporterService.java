package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.SupporterDAO;
import be.kuleuven.tennistoernooijava.model.Supporter;

public class SupporterService {
    private final SupporterDAO supporterDAO;

    public SupporterService(SupporterDAO supporterDAO) {this.supporterDAO = supporterDAO;}

    public Supporter create(int spelerID, int clubID){
        Supporter supporter = new Supporter();
        supporter.setClubId(clubID);
        supporter.setSpelerId(spelerID);
        return supporterDAO.create(supporter);
    }
}
