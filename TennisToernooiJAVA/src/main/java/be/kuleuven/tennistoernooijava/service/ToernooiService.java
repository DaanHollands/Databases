package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.ToernooiDAO;
import be.kuleuven.tennistoernooijava.model.Toernooien;

public class ToernooiService {
    private final ToernooiDAO toernooiDAO;

    public ToernooiService(ToernooiDAO toernooiDAO) {this.toernooiDAO = toernooiDAO;}

    public Toernooien create(int beginDatumID, int eindDatumID, int clubID){
        Toernooien toernooi = new Toernooien();
        toernooi.setClubId(clubID);
        toernooi.setBeginDatumId(beginDatumID);
        toernooi.setEindDatumId(eindDatumID);
        return toernooiDAO.create(toernooi);
    }
}
