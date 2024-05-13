package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.BallenraperDAO;
import be.kuleuven.tennistoernooijava.models.*;

import java.util.List;
import java.util.Set;

public class BallenraperService {
    private final BallenraperDAO ballenraperDAO;

    public BallenraperService(BallenraperDAO ballenraperDAO) {
        this.ballenraperDAO = ballenraperDAO;
    }

    public void createBallenraper(Spelers speler, Finales finale) {
        Ballenrapers ballenraper = new Ballenrapers();
        ballenraper.setSpeler(speler);
        ballenraper.addFinale(finale);
        ballenraper = ballenraperDAO.create(ballenraper);
        finale.addBallenraper(ballenraper);
    }

    public void removeBallenraper(Spelers speler, Finales finale) {
        List<Ballenrapers> ballenrapers = ballenraperDAO.getBallenraperFromSpeler(speler);
        ballenrapers.forEach(e -> {
            if(e.getFinales().equals(finale)) {
                finale.getDeelnamens().remove(e);
                ballenraperDAO.delete(e);
            }
        });
    }

    public boolean isBallenraper(Finales finale, Spelers speler) {
        Set<Ballenrapers> ballenrapers = finale.getBallenrapers();
        var gevonden = ballenrapers.stream()
                .filter(ballenraper -> ballenraper.getSpeler().equals(speler))
                .findFirst();
        return gevonden.isPresent();
    }


}
