package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.ReeksenDAO;
import be.kuleuven.tennistoernooijava.dao.ToernooienDAO;
import be.kuleuven.tennistoernooijava.models.*;

import java.time.LocalDate;
import java.util.Set;

public class ToernooiService {
    private final ToernooienDAO toernooienDAO;

    public ToernooiService(ToernooienDAO toernooienDAO) {
        this.toernooienDAO = toernooienDAO;
    }

    public Toernooien createToernooi(
            Tennisclubs organisatorClub,
            Integer beginDag, Integer beginMaand, Integer beginJaar,
            Integer eindDag, Integer eindMaand, Integer eindJaar
    ) {
        if (beginDag<=0 || beginDag >31 ){
            throw new IllegalDateException("Ongeldige begindag");
        }
        if (beginMaand<=0 || beginMaand >12 ){
            throw new IllegalDateException("Ongeldige beginmaand");
        }
        if (beginJaar<=2023 ){
            throw new IllegalDateException("Ongeldig beginjaar");
        }
        if (eindDag<=0 || eindDag >31){
            throw new IllegalDateException("Ongeldige einddag");
        }
        if (eindMaand<=0 || eindMaand >12 ){
            throw new IllegalDateException("Ongeldige eindmaand");
        }
        if (eindJaar<=2023 ){
            throw new IllegalDateException("Ongeldige eindjaar");
        }
        valideerDatum(beginDag,beginMaand,beginJaar,eindDag,eindMaand,eindJaar);


        Toernooien toernooien = new Toernooien();
        Datums beginDatum = new Datums();
        beginDatum.setDag(beginDag);
        beginDatum.setMaand(beginMaand);
        beginDatum.setJaar(beginJaar);

        Datums eindDatum = new Datums();
        eindDatum.setDag(eindDag);
        eindDatum.setMaand(eindMaand);
        eindDatum.setJaar(eindJaar);
        DatumsDAO datumsDAO = new DatumsDAO();
        beginDatum = datumsDAO.create(beginDatum);
        eindDatum = datumsDAO.create(eindDatum);

        toernooien.setBeginDatumID(beginDatum);
        toernooien.setEindDatumID(eindDatum);

        toernooien.setClubOrganistorID(organisatorClub);
        toernooien = toernooienDAO.create(toernooien);
        organisatorClub.addToernooi(toernooien);
        return toernooien;
    }

    public Set<Reeksen> addReeks(Toernooien toernooi, String reeksNiveau) {
        Reeksen reeks = new ReeksenService(new ReeksenDAO()).getReeks(reeksNiveau);
        toernooi.addReeks(reeks);
        Toernooien newToernooi = toernooienDAO.update(toernooi);
        return newToernooi.getReeksen();
    }

    public Set<Matchen> getAllMatchen(Toernooien toernooi) {
        return toernooi.getMatchen();
    }

    public static void valideerDatum(Integer beginDag, Integer beginMaand, Integer beginJaar,
                                     Integer eindDag, Integer eindMaand, Integer eindJaar){
        LocalDate eersteDatum = LocalDate.of(beginJaar,beginMaand,beginDag );
        LocalDate tweedeDatum = LocalDate.of(eindJaar, eindMaand, eindDag);

        if(tweedeDatum.isBefore(eersteDatum)){
            throw new IllegalDateException("Tweede datum komt voor de eerste datum!");
        }
    }

}
