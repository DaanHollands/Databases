package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.VeldDAO;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;
import be.kuleuven.tennistoernooijava.model.Veld;

public class VeldService {
    private final VeldDAO veldDAO;

    public VeldService(VeldDAO veldDAO) {this.veldDAO = veldDAO;}

    public Veld create(int clubID, VeldSoort veldSoort){
        Veld veld = new Veld();
        veld.setClubId(clubID);
        veld.setVeldSoort(veldSoort.ordinal());
        return veldDAO.create(veld);
    }
}
