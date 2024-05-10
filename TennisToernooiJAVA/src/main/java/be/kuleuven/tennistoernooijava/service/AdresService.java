package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.AdresDAO;
import be.kuleuven.tennistoernooijava.database.Adressen;

public class AdresService {
    private final AdresDAO adresDAO;

    public AdresService(AdresDAO adresDAO) {this.adresDAO = adresDAO;}

    public Adressen create(Integer postcode, String straatnaam, Integer straatnummer) {
        Adressen adres = new Adressen();
        adres.setPostcode(postcode);
        adres.setStraatnaam(straatnaam);
        adres.setStraatnummer(straatnummer);
        return adresDAO.create(adres);
    }

    public Adressen getOrCreate(Integer postcode, String straatnaam, Integer straatnummer) {
        Adressen adres = adresDAO.getAdresFrom(postcode, straatnaam, straatnummer);
        if(adres == null) {
            return create(postcode, straatnaam, straatnummer);
        }
        return adres;
    }
}
