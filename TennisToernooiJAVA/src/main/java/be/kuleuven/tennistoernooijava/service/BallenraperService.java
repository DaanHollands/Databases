package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.BallenraperDAO;
import be.kuleuven.tennistoernooijava.enums.Plaatsen;
import be.kuleuven.tennistoernooijava.model.Ballenraper;

public class BallenraperService {
    private final BallenraperDAO ballenraperDAO;

    public BallenraperService(BallenraperDAO ballenraperDAO) {this.ballenraperDAO = ballenraperDAO;}

    public Ballenraper create (int spelerID, Plaatsen plaats) {
        Ballenraper ballenraper = new Ballenraper();
        ballenraper.setSpelerId(spelerID);
        ballenraper.setPlaatsId(plaats.ordinal());
        return ballenraperDAO.create(ballenraper);
    }
}
