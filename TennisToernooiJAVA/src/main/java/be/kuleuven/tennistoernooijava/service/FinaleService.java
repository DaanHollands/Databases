package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.model.Finales;

public class FinaleService {
    private final FinaleDAO finaleDAO;

    public FinaleService(FinaleDAO finaleDAO) {this.finaleDAO = finaleDAO;}

    public Finales create (int matchID, int scheidsrechterID){
        Finales finale = new Finales();
        finale.setMatchId(matchID);
        finale.setScheidsrechter(scheidsrechterID);
        return finaleDAO.create(finale);
    }
}
