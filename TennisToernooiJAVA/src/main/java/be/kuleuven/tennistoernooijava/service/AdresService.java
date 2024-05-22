package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalNumberException;
import be.kuleuven.tennistoernooijava.dao.AdresDAO;
import be.kuleuven.tennistoernooijava.models.Adressen;

public class AdresService {
    private final AdresDAO adresDAO;

    public AdresService(AdresDAO adresDAO) {this.adresDAO = adresDAO;}

    public Adressen create(Integer postcode, String straatnaam, Integer straatnummer) {
        Adressen adres = new Adressen();

        if(adres.getPostcode() == null || adres.getPostcode() <0 || adres.getPostcode() > 9999){
            throw new IllegalNumberException("Ongeldige postcode");
        }
        if(adres.getStraatnummer() == null || adres.getStraatnummer() < 0 ){
            throw new IllegalNumberException("Ongeldige straatnummer");
        }

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
