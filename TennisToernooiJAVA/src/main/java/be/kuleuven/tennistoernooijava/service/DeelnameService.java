package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.DeelnamenDAO;
import be.kuleuven.tennistoernooijava.models.Deelnamen;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;

import java.util.List;
import java.util.Set;

public class DeelnameService {
    private final DeelnamenDAO deelnamenDAO;

    public DeelnameService(DeelnamenDAO deelnamenDAO) {
        this.deelnamenDAO = deelnamenDAO;
    }

    public void createDeelname(Spelers speler, Matchen match) {
        Deelnamen deelnamen = new Deelnamen();
        deelnamen.setMatchID(match);
        deelnamen.setSpelerID(speler);
        deelnamen = deelnamenDAO.create(deelnamen);
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
