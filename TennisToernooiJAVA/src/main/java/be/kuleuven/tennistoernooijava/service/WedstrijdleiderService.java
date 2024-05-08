package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.WedstrijdleiderDAO;
import be.kuleuven.tennistoernooijava.model.Wedstrijdleiders;

public class WedstrijdleiderService {
    private final WedstrijdleiderDAO westrijdleiderDAO;

    public WedstrijdleiderService(WedstrijdleiderDAO westrijdleiderDAO) {this.westrijdleiderDAO = westrijdleiderDAO;}

    public Wedstrijdleiders create(int spelerID){
        Wedstrijdleiders wedstrijdleider = new Wedstrijdleiders();
        wedstrijdleider.setSpelerId(spelerID);
        return westrijdleiderDAO.create(wedstrijdleider);
    }
}
