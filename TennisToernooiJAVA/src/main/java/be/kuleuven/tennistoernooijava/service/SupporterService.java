package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.SupporterDAO;
import be.kuleuven.tennistoernooijava.models.*;

import java.util.List;
import java.util.Set;

public class SupporterService {
    private final SupporterDAO supporterDAO;

    public SupporterService(SupporterDAO supporterDAO) {
        this.supporterDAO = supporterDAO;
    }

    public void createSupporter(Spelers speler, Finales finale, Tennisclubs club) {
        Supporters supporter = new Supporters();
        supporter.addFinale(finale);
        supporter.setSupporterID(speler);
        supporter.setClubID(club);
        supporter = supporterDAO.create(supporter);
        finale.addSupporter(supporter);
    }

    public void removeSupporter(Spelers speler, Finales finale) {
        List<Supporters> supporterslist = supporterDAO.getSupporterFromSpeler(speler);
        supporterslist.forEach(e -> {
            if(e.getFinales().equals(finale)) {
                finale.getDeelnamens().remove(e);
                supporterDAO.delete(e);
            }
        });
    }

    public boolean isSupporter(Finales finale, Spelers speler) {
        Set<Supporters> supporters = finale.getSupporters();
        var gevonden = supporters.stream()
                .filter(supporter -> supporter.getSupporterID().equals(speler))
                .findFirst();
        return gevonden.isPresent();
    }
}
