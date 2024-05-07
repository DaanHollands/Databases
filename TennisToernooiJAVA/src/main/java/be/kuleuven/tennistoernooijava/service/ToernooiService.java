package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.ToernooiDAO;
import be.kuleuven.tennistoernooijava.model.Toernooi;

public class ToernooiService {
    private final ToernooiDAO toernooiDAO;

    public ToernooiService(ToernooiDAO toernooiDAO) {this.toernooiDAO = toernooiDAO;}

    public Toernooi create(int beginDatumID, int eindDatumID, int clubID){
        Toernooi toernooi = new Toernooi();
        toernooi.setClubId(clubID);
        toernooi.setBeginDatumId(beginDatumID);
        toernooi.setEindDatumId(eindDatumID);
        return toernooiDAO.create(toernooi);
    }
}
