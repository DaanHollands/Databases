package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.model.Finale;

public class FinaleService {
    private final FinaleDAO finaleDAO;

    public FinaleService(FinaleDAO finaleDAO) {this.finaleDAO = finaleDAO;}

    public Finale create (int matchID, int scheidsrechterID){
        Finale finale = new Finale();
        finale.setMatchId(matchID);
        finale.setScheidsrechter(scheidsrechterID);
        return finaleDAO.create(finale);
    }
}
