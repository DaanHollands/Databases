package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.VeldDAO;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;
import be.kuleuven.tennistoernooijava.model.Velden;

public class VeldService {
    private final VeldDAO veldDAO;

    public VeldService(VeldDAO veldDAO) {this.veldDAO = veldDAO;}

    public Velden create(int clubID, VeldSoort veldSoort){
        Velden veld = new Velden();
        veld.setClubId(clubID);
        veld.setVeldSoort(veldSoort.ordinal());
        return veldDAO.create(veld);
    }
}
