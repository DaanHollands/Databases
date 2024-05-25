package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.EmptyInputException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalReeksException;
import be.kuleuven.tennistoernooijava.Exceptions.InvalidInputException;
import be.kuleuven.tennistoernooijava.Exceptions.InvalidPhoneNumberException;
import be.kuleuven.tennistoernooijava.dao.ReeksenDAO;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.Datums;
import be.kuleuven.tennistoernooijava.models.Reeksen;
import be.kuleuven.tennistoernooijava.models.Spelers;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class ReeksenService {
    private final ReeksenDAO reeksenDAO;

    public ReeksenService(ReeksenDAO reeksenDAO) {
        this.reeksenDAO = reeksenDAO;
    }

    public Reeksen getReeks(Integer reeksNiveau, ReeksenWaardes reeks) {
        Reeksen checkReeks = reeksenDAO.findFromNivea(reeks, reeksNiveau);
        if(checkReeks != null) {
            return checkReeks;
        }
        Reeksen newReeks = new Reeksen();
        newReeks.setReeks(reeks);
        newReeks.setNiveau(reeksNiveau);
        newReeks = reeksenDAO.create(newReeks);
        return newReeks;
    }

    public static boolean canJoinReeks(Spelers speler, Reeksen reeks) {
        if(reeks.getReeks().equals(ReeksenWaardes.MANNEN)) {
            return speler.getGeslacht() == Geslachten.M;
        }
        if(reeks.getReeks().equals(ReeksenWaardes.VROUWEN)) {
            return speler.getGeslacht() == Geslachten.V;
        }
        if(reeks.getReeks().equals(ReeksenWaardes.THEY)) {
            return speler.getGeslacht() == Geslachten.X;
        }
        if(reeks.getReeks().equals(ReeksenWaardes.KINDEREN)) {
            return berekenLeeftijd(speler.getDatumID()) < 18;
        }
        return false;
    }

    private static int berekenLeeftijd(Datums datumID) {
        LocalDate nu = LocalDate.now();
        LocalDate geboortedatum = LocalDate.of(datumID.getJaar(), datumID.getMaand(), datumID.getDag());
        return Period.between(geboortedatum, nu).getYears();
    }

    public void validateExceptions(String reeksNiveau, ReeksenWaardes reeksenWaarde)
    {
        if(reeksNiveau == null || reeksNiveau.isEmpty()) {
            throw new EmptyInputException("Het reeksNiveau is niet ingevuld, dit is een getal tussen 0 en 10");
        }
        else if(reeksNiveau.contains("[a-zA-Z]+")) {
            throw new InvalidInputException("Je mag enkel nummers ingeven hier");
        }
        else if(Integer.parseInt(reeksNiveau) < 0) {
            throw new InvalidInputException("Het mag geen negatief nummer zijn");
        }
        if(reeksenWaarde == null) {
            throw new IllegalReeksException("Je moet eerst een reeks kiezen");
        }
    }
}
