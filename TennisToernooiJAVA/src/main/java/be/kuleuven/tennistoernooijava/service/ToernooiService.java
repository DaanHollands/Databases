package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.Exceptions.IlligalWedstrijdleiderException;
import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;
import javafx.css.Match;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ToernooiService {
    private final ToernooienDAO toernooienDAO;

    public ToernooiService(ToernooienDAO toernooienDAO) {
        this.toernooienDAO = toernooienDAO;
    }

    public Toernooien createToernooi(
            Tennisclubs organisatorClub,
            Integer beginDag, Integer beginMaand, Integer beginJaar,
            Integer eindDag, Integer eindMaand, Integer eindJaar,
            Spelers nieuweWestrijdleider
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
        if(new WedstrijdleiderDAO().find(nieuweWestrijdleider.getSpelerID()) != null) {
            throw new IlligalWedstrijdleiderException("Je bent al een wedstrijdleider van een toernooi");
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

        Wedstrijdleider wedstrijdleider = new Wedstrijdleider();
        wedstrijdleider.setSpeler(nieuweWestrijdleider);
        wedstrijdleider = new WedstrijdleiderDAO().create(wedstrijdleider);

        toernooien.setWedstrijdleider(wedstrijdleider);
        toernooien.setBeginDatumID(beginDatum);
        toernooien.setEindDatumID(eindDatum);

        toernooien.setClubOrganistorID(organisatorClub);
        toernooien = toernooienDAO.create(toernooien);


        wedstrijdleider.setToernooi(toernooien);
        organisatorClub.addToernooi(toernooien);
        return toernooien;
    }

    public Set<Matchen> getAllMatchen(Toernooien toernooi) {
        return toernooi.getMatchen();
    }

    public Matchen addSpelerToReeks(Deelnamen deelnamen, Reeksen reeks, Toernooien toernooi) {
        Optional<Matchen> match = toernooi.getMatchen().stream()
                .filter(e -> e.getIsFirstMatch() && e.getDeelnamens().size() != 2)
                .peek(m -> m.addDeelname(deelnamen))
                .findFirst();

        return match.orElse(null);
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
