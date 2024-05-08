package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.SpelersEmailAdresDAO;
import be.kuleuven.tennistoernooijava.model.SpelersEmailadressen;

public class SpelerEmailAdresService {
    private final SpelersEmailAdresDAO spelersEmailAdresDAO;

    public SpelerEmailAdresService(SpelersEmailAdresDAO spelersEmailAdresDAO) {this.spelersEmailAdresDAO = spelersEmailAdresDAO;}

    public SpelersEmailadressen create(int spelerID, String emailadres){
        SpelersEmailadressen spelersEmailadres = new SpelersEmailadressen();
        spelersEmailadres.setSpelerId(spelerID);
        spelersEmailadres.setEmail(emailadres);
        return spelersEmailAdresDAO.create(spelersEmailadres);
    }
}
