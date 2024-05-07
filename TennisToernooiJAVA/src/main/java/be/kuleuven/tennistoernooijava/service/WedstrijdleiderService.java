package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.WedstrijdleiderDAO;
import be.kuleuven.tennistoernooijava.model.Wedstrijdleider;

public class WedstrijdleiderService {
    private final WedstrijdleiderDAO westrijdleiderDAO;

    public WedstrijdleiderService(WedstrijdleiderDAO westrijdleiderDAO) {this.westrijdleiderDAO = westrijdleiderDAO;}

    public Wedstrijdleider create(int spelerID){
        Wedstrijdleider wedstrijdleider = new Wedstrijdleider();
        wedstrijdleider.setSpelerId(spelerID);
        return westrijdleiderDAO.create(wedstrijdleider);
    }
}
