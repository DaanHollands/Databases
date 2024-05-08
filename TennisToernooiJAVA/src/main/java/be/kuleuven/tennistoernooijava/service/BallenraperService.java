package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.BallenraperDAO;
import be.kuleuven.tennistoernooijava.enums.Plaatsen;
import be.kuleuven.tennistoernooijava.model.Ballenrapers;

public class BallenraperService {
    private final BallenraperDAO ballenraperDAO;

    public BallenraperService(BallenraperDAO ballenraperDAO) {this.ballenraperDAO = ballenraperDAO;}

//    public Ballenrapers create ( Plaatsen plaats) {
//        Ballenrapers ballenraper = new Ballenrapers();
//        ballenraper.setSpelerId(spelerID);
//        ballenraper.setPlaatsId(plaats.ordinal());
//        return ballenraperDAO.create(ballenraper);
//    }
}
