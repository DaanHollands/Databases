package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalWedstrijdleiderException;
import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.enums.Uitslagen;
import be.kuleuven.tennistoernooijava.models.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class ToernooiService {
    private final ToernooienDAO toernooienDAO;

    public ToernooiService(ToernooienDAO toernooienDAO) {
        this.toernooienDAO = toernooienDAO;
    }

    public void createToernooi(
            Tennisclubs organisatorClub,
            Integer beginDag, Integer beginMaand, Integer beginJaar,
            Integer eindDag, Integer eindMaand, Integer eindJaar,
            Spelers nieuweWestrijdleider
    ) {
        Optional<RuntimeException> exception = validateExceptions(beginDag, beginMaand, beginJaar, eindDag, eindMaand, eindJaar, nieuweWestrijdleider);
        if(exception.isPresent()) {
            throw exception.get();
        }

        Toernooien toernooien = new Toernooien();
        Wedstrijdleider wedstrijdleider = new Wedstrijdleider();
        wedstrijdleider.setSpeler(nieuweWestrijdleider);
        wedstrijdleider = new WedstrijdleiderDAO().create(wedstrijdleider);

        toernooien.setWedstrijdleider(wedstrijdleider);
        toernooien.setBeginDatumID(createNewDatum(beginDag, beginMaand, beginJaar));
        toernooien.setEindDatumID(createNewDatum(eindDag, eindMaand, eindJaar));

        toernooien.setClubOrganistorID(organisatorClub);
        toernooien = toernooienDAO.create(toernooien);

        wedstrijdleider.setToernooi(toernooien);
        organisatorClub.addToernooi(toernooien);
    }

    public void updateMatchen(Toernooien toernooien) {
        Set<Matchen> matchen = toernooien.getMatchen();
        for(Matchen match : matchen) {
            if(match.getUitslag() != null) {
                Deelnamen gewonne;
                Deelnamen verloren;
                if(match.getUitslag().equals(Uitslagen.GEWONNEN)) {
                    gewonne = (Deelnamen) match.getDeelnamens().toArray()[0];
                    verloren = (Deelnamen) match.getDeelnamens().toArray()[1];
                } else if(match.getUitslag().equals(Uitslagen.VERLOREN)) {
                    gewonne = (Deelnamen) match.getDeelnamens().toArray()[1];
                    verloren = (Deelnamen) match.getDeelnamens().toArray()[0];
                } else {
                    continue;
                }
                for(Matchen newMatch : matchen) {
                    if((newMatch.getMatchRonde() == match.getMatchRonde()+1) && (newMatch.getDeelnamens().size() < 2) && (newMatch.getReeks().equals(match.getReeks()))) {
                        if(matchen.stream().filter(m -> m.getMatchRonde() == match.getMatchRonde()+1).anyMatch(nm -> nm.getDeelnamens().contains(gewonne))) {
                            break;
                        }
                        gewonne.setMatchID(newMatch);
                        newMatch.addDeelname(gewonne);
                        new MatchenDAO().update(newMatch);
                        new DeelnamenDAO().update(gewonne);
                        break;
                    }
                }
            }
        }
    }

    public Optional<RuntimeException> valideerDatum(Integer beginDag, Integer beginMaand, Integer beginJaar,
                                     Integer eindDag, Integer eindMaand, Integer eindJaar){
        LocalDate eersteDatum = LocalDate.of(beginJaar,beginMaand,beginDag );
        LocalDate tweedeDatum = LocalDate.of(eindJaar, eindMaand, eindDag);

        if(tweedeDatum.isBefore(eersteDatum)){
            return Optional.of(new IllegalDateException("Tweede datum komt voor de eerste datum!"));
        }
        return Optional.empty();
    }

    private Datums createNewDatum(Integer dag, Integer maand, Integer jaar) {
        Datums datum = new Datums();
        datum.setDag(dag);
        datum.setMaand(maand);
        datum.setJaar(jaar);
        return new DatumsDAO().create(datum);
    }

    private Optional<RuntimeException> validateExceptions(
            Integer beginDag, Integer beginMaand, Integer beginJaar,
            Integer eindDag, Integer eindMaand, Integer eindJaar,
            Spelers nieuweWestrijdleider
    )
    {
        if (beginDag<=0 || beginDag >31 ){
            return Optional.of(new IllegalDateException("Ongeldige begindag"));
        }
        if (beginMaand<=0 || beginMaand >12 ){
            return Optional.of(new IllegalDateException("Ongeldige beginmaand"));
        }
        if (beginJaar<=2023 ){
            return Optional.of(new IllegalDateException("Ongeldig beginjaar"));
        }
        if (eindDag<=0 || eindDag >31){
            return Optional.of(new IllegalDateException("Ongeldige einddag"));
        }
        if (eindMaand<=0 || eindMaand >12 ){
            return Optional.of(new IllegalDateException("Ongeldige eindmaand"));
        }
        if (eindJaar<=2023 ){
            return Optional.of(new IllegalDateException("Ongeldig eindjaar"));
        }
        if(new WedstrijdleiderDAO().find(nieuweWestrijdleider.getSpelerID()) != null) {
            return Optional.of(new IllegalWedstrijdleiderException("Je bent al een wedstrijdleider van een toernooi"));
        }
        return valideerDatum(beginDag,beginMaand,beginJaar,eindDag,eindMaand,eindJaar);
    }

}
