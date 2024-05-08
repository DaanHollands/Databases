package be.kuleuven.tennistoernooijava.service;


import be.kuleuven.tennistoernooijava.dao.DeelnameDAO;
import be.kuleuven.tennistoernooijava.model.Deelnamen;

public class DeelnameService {
    private final DeelnameDAO deelnameDAO;

    public DeelnameService(DeelnameDAO deelnameDAO) {this.deelnameDAO = deelnameDAO;}

    public Deelnamen create(int matchID, int spelerID, String vraag){
        Deelnamen deelname = new Deelnamen();
        deelname.setMatchID(matchID);
        deelname.setSpelerID(spelerID);
        deelname.setVraag(vraag);
        return deelnameDAO.create(deelname);
    }
}
