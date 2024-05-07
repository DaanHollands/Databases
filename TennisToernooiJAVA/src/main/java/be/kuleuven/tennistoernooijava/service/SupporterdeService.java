package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.SupporterdeDAO;
import be.kuleuven.tennistoernooijava.model.Supporterde;

public class SupporterdeService {
    private final SupporterdeDAO supporterdeDAO;

    public SupporterdeService(SupporterdeDAO supporterdeDAO) {this.supporterdeDAO = supporterdeDAO;}

    public Supporterde create(int supporterID, int finaleID){
        Supporterde supporterde = new Supporterde();
        supporterde.setFinaleId(finaleID);
        supporterde.setSupperterId(supporterID);
        return supporterdeDAO.create(supporterde);
    }
}
