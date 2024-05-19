package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalReeksException;
import be.kuleuven.tennistoernooijava.dao.DeelnamenDAO;
import be.kuleuven.tennistoernooijava.dao.ToernooienDAO;
import be.kuleuven.tennistoernooijava.models.*;

import java.util.List;
import java.util.Set;

public class DeelnameService {
    private final DeelnamenDAO deelnamenDAO;

    public DeelnameService(DeelnamenDAO deelnamenDAO) {
        this.deelnamenDAO = deelnamenDAO;
    }

    public void createDeelname(Spelers speler, Matchen match) {
        if(speler.getRanking() > match.getReeks().getNiveau()) {
            throw new IllegalReeksException("Je mag niet inschrijven voor deze reeks, je het ranking is hoger dan de reeks niveau nummer");
        }
        Deelnamen deelnamen = new Deelnamen();
        deelnamen.setSpelerID(speler);
        deelnamen.setMatchID(match);
        deelnamen = new DeelnamenDAO().create(deelnamen);
        match.addDeelname(deelnamen);
    }

    public void removeDeelname(Spelers speler, Matchen match) {
        List<Deelnamen> deelnamenlist = deelnamenDAO.getDeelnamenFromSpeler(speler);
        deelnamenlist.forEach(e -> {
            if(e.getMatchID().equals(match)) {
                match.getDeelnamens().remove(e);
                deelnamenDAO.delete(e);
            }
        });
    }

    public boolean isDeelnemer(Matchen match, Spelers speler) {
        Set<Deelnamen> deelnamen = match.getDeelnamens();
        var gevonden = deelnamen.stream()
                .filter(deelname -> deelname.getSpelerID().equals(speler))
                .findFirst();
        return gevonden.isPresent();
    }
}
