package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.GeraapteBalDAO;
import be.kuleuven.tennistoernooijava.model.GeraapteBal;

public class GeraapteBallenService {
    private final GeraapteBalDAO geraapteBalDAO;

    public GeraapteBallenService(GeraapteBalDAO geraapteBalDAO) {this.geraapteBalDAO = geraapteBalDAO;}

    public GeraapteBal create(int ballenRaperID, int finaleID){
        GeraapteBal geraapteBal = new GeraapteBal();
        geraapteBal.setBallenraperId(ballenRaperID);
        geraapteBal.setFinaleId(finaleID);
        return geraapteBalDAO.create(geraapteBal);
    }

}
