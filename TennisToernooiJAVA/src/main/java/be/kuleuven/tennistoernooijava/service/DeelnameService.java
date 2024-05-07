package be.kuleuven.tennistoernooijava.service;


import be.kuleuven.tennistoernooijava.dao.DeelnameDAO;
import be.kuleuven.tennistoernooijava.model.Deelname;

public class DeelnameService {
    private final DeelnameDAO deelnameDAO;

    public DeelnameService(DeelnameDAO deelnameDAO) {this.deelnameDAO = deelnameDAO;}

    public be.kuleuven.tennistoernooijava.model.Deelname create(int matchID, int spelerID, String vraag){
        be.kuleuven.tennistoernooijava.model.Deelname deelname = new be.kuleuven.tennistoernooijava.model.Deelname();
        deelname.setMatchId(matchID);
        deelname.setSpelerId(spelerID);
        deelname.setVraag(vraag);
        return deelnameDAO.create(deelname);
    }
}
