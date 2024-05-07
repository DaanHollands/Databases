package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.SpelersEmailAdresDAO;
import be.kuleuven.tennistoernooijava.model.Speler;
import be.kuleuven.tennistoernooijava.model.SpelersEmailadres;

public class SpelerEmailAdresService {
    private final SpelersEmailAdresDAO spelersEmailAdresDAO;

    public SpelerEmailAdresService(SpelersEmailAdresDAO spelersEmailAdresDAO) {this.spelersEmailAdresDAO = spelersEmailAdresDAO;}

    public SpelersEmailadres create(int spelerID, String emailadres){
        SpelersEmailadres spelersEmailadres = new SpelersEmailadres();
        spelersEmailadres.setSpelerId(spelerID);
        spelersEmailadres.setEmail(emailadres);
        return spelersEmailAdresDAO.create(spelersEmailadres);
    }
}
