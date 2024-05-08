package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.MatchDAO;
import be.kuleuven.tennistoernooijava.model.Matchen;

public class MatchService {
    private MatchDAO matchDAO;

    public MatchService(MatchDAO matchDAO) {this.matchDAO = matchDAO;}

    public Matchen create(int datumID, int scoreThuis, int scoreUit, int uitslagID, int veldID, int wedstijdleiderID, int toernooiID){
        Matchen match = new Matchen();
        match.setDatum(datumID);
        match.setScoreThuis(scoreThuis);
        match.setScoreUit(scoreUit);
        match.setUitslag(uitslagID);
        match.setVeld(veldID);
        match.setWedstrijdleider(wedstijdleiderID);
        match.setToernooiId(toernooiID);
        return matchDAO.create(match);
    }
}
