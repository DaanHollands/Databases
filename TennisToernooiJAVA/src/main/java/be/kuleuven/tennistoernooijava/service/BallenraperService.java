package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.BallenraperDAO;
import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.enums.Posities;
import be.kuleuven.tennistoernooijava.models.*;

import java.util.List;
import java.util.Set;

public class BallenraperService {
    private final BallenraperDAO ballenraperDAO;

    public BallenraperService(BallenraperDAO ballenraperDAO) {
        this.ballenraperDAO = ballenraperDAO;
    }

    public void createBallenraper(Spelers speler, Finales finale, Posities positie) {
        Ballenrapers ballenraper = new Ballenrapers();
        ballenraper.setSpeler(speler);
        ballenraper.setPlaats(positie);
        ballenraper.addFinale(finale);
        ballenraper = ballenraperDAO.create(ballenraper);
        finale.addBallenraper(ballenraper);
        new FinaleDAO().update(finale);
    }

    public boolean isBallenraper(Finales finale, Spelers speler) {
        Set<Ballenrapers> ballenrapers = finale.getBallenrapers();
        var gevonden = ballenrapers.stream()
                .filter(ballenraper -> ballenraper.getSpeler().equals(speler))
                .findFirst();
        return gevonden.isPresent();
    }
}
