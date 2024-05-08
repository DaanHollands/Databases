package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.GeraapteBalDAO;
import be.kuleuven.tennistoernooijava.model.GeraapteBallen;

public class GeraapteBallenService {
    private final GeraapteBalDAO geraapteBalDAO;

    public GeraapteBallenService(GeraapteBalDAO geraapteBalDAO) {this.geraapteBalDAO = geraapteBalDAO;}

    public GeraapteBallen create(int ballenRaperID, int finaleID){
        GeraapteBallen geraapteBal = new GeraapteBallen();
        geraapteBal.setBallenraperId(ballenRaperID);
        geraapteBal.setFinaleId(finaleID);
        return geraapteBalDAO.create(geraapteBal);
    }

}
