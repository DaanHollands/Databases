package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.AdresDAO;
import be.kuleuven.tennistoernooijava.model.Adres;

public class AdresService {
    private final AdresDAO adresDAO;

    public AdresService(AdresDAO adresDAO) {this.adresDAO = adresDAO;}

    public Adres create(int postcode, String straatnaam, int straatnummer) {
        Adres adres = new Adres();
        adres.setPostcode(postcode);
        adres.setStraatnaam(straatnaam);
        adres.setStraatnummer(straatnummer);
        return adresDAO.create(adres);
    }
}
