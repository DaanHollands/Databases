package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.Exceptions.*;
import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.SpelerEmailadressenDAO;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.models.Datums;
import be.kuleuven.tennistoernooijava.models.Spelers;

import java.util.Optional;
import java.util.Set;

public class SpelerService {
    private final SpelersDAO spelersDAO;

    public SpelerService(SpelersDAO spelersDAO) {
        this.spelersDAO = spelersDAO;
    }

    public Spelers createSpeler(
            String naam,
            String telefoonnummer,
            Integer geboorteDag, Integer geboorteMaand, String geboorteJaar,
            String gewicht, String lengte, String ranking,
            Geslachten geslacht,
            String email
    )
    {
        Optional<InvalidEmailException> emailException = checkEmailException(email);
        if(emailException.isPresent()) {
            throw emailException.get();
        }
        Optional<RuntimeException> exception = checkDefaultExceptions(naam, telefoonnummer, geboorteDag, geboorteMaand, geboorteJaar, gewicht, lengte, ranking);
        if(exception.isPresent()) {
            throw exception.get();
        }

        Spelers speler = new Spelers();
        speler = initializeSpeler(speler, naam, telefoonnummer, geboorteDag, geboorteMaand, geboorteJaar, gewicht, lengte, ranking, geslacht);
        speler = spelersDAO.create(speler);

        SpelerEmailadressen spelerEmailadressen = new SpelerEmailadressen();
        spelerEmailadressen.setEmail(email);
        speler.addEmails(spelerEmailadressen);
        spelerEmailadressen.setSpelerID(speler);
        SpelerEmailadressenDAO emailadressenDAO = new SpelerEmailadressenDAO();
        emailadressenDAO.create(spelerEmailadressen);

        return speler;
    }

    public Spelers updateSpeler(
            Spelers speler,
            String naam,
            String telefoonnummer,
            Integer geboorteDag, Integer geboorteMaand, String geboorteJaar,
            String gewicht, String lengte, String ranking,
            Geslachten geslacht
    )
    {
        Optional<RuntimeException> exception = checkDefaultExceptions(naam, telefoonnummer, geboorteDag, geboorteMaand, geboorteJaar, gewicht, lengte, ranking);
        if(exception.isPresent()) {
            throw exception.get();
        }
        speler = spelersDAO.update(initializeSpeler(speler, naam, telefoonnummer, geboorteDag, geboorteMaand, geboorteJaar, gewicht, lengte, ranking, geslacht));
        return speler;
    }

    public Spelers initializeSpeler(
            Spelers speler,
            String naam,
            String telefoonnummer,
            Integer geboorteDag, Integer geboorteMaand, String geboorteJaar,
            String gewicht, String lengte, String ranking,
            Geslachten geslacht
    )
    {
        speler.setNaam(naam);
        speler.setTelefoonnummer(telefoonnummer);
        speler.setGewicht(Integer.parseInt(gewicht));
        Datums geboorteDatums = new Datums();
        geboorteDatums.setDag(geboorteDag);
        geboorteDatums.setJaar(Integer.parseInt(geboorteJaar));
        geboorteDatums.setMaand(geboorteMaand);
        speler.setDatumID(new DatumsDAO().create(geboorteDatums));
        speler.setGeslacht(geslacht);
        speler.setRanking(Integer.parseInt(ranking));
        speler.setLengte(Integer.parseInt(lengte));
        speler.setTennisclubID(speler.getTennisclubID());
        return speler;
    }

    public Spelers getSpeler(Integer spelerID) {
        Spelers speler = spelersDAO.find(spelerID);
        if(speler == null) {
            throw new SpelerNotFoundException("Speler bestaat nog niet");
        }
        return speler;
    }

    public void joinClub(Tennisclubs club, Spelers speler) {
        speler.setTennisclubID(club);
        spelersDAO.update(speler);
    }

    public void removeEmailFromSpeler(Integer spelerID, String email) {
        Set<SpelerEmailadressen> emailadressen = spelersDAO.find(spelerID).getEmails();
        for (SpelerEmailadressen spelerEmailadres : emailadressen) {
            if(spelerEmailadres.getEmail().equals(email)) {
                SpelerSessie.getSessie().getSpeler().removeEmailAdres(spelerEmailadres);
                spelersDAO.update(SpelerSessie.getSessie().getSpeler());
                return;
            }
        }
    }

    public Optional<InvalidEmailException> checkEmailException(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return Optional.of(new InvalidEmailException("Ongeldige email!"));
        }
        String domain = parts[1];
        if(!domain.contains(".")) {
            return Optional.of(new InvalidEmailException("Ongeldige email!"));
        }
        return Optional.empty();
    }

    private Optional<RuntimeException> checkDefaultExceptions(
            String naam, String telefoonnummer, Integer geboorteDag, Integer geboorteMaand, String geboorteJaar,
            String gewicht, String lengte, String ranking
    )
    {
        if(naam.isEmpty()) {
            return Optional.of(new EmptyInputException("Geen naam ingegeven!"));
        }
        if (telefoonnummer == null || telefoonnummer.contains("[a-zA-Z]+")) {
            return Optional.of(new InvalidPhoneNumberException("Ongeldige telefoonnummer!"));
        }
        if (gewicht == null || gewicht.isEmpty() || gewicht.contains("[a-zA-Z]+") || Integer.parseInt(gewicht) < 0) {
            return Optional.of(new InvalidInputException("Ongeldige gewicht!"));
        }
        if (lengte == null || lengte.isEmpty() || lengte.contains("[a-zA-Z]+") || Integer.parseInt(lengte) < 0) {
            return Optional.of(new InvalidInputException("Ongeldige lengte!"));
        }
        if (geboorteJaar == null || geboorteJaar.isEmpty() || geboorteJaar.contains("[a-zA-Z]+") || Integer.parseInt(geboorteJaar) < 0) {
            return Optional.of(new IllegalDateException("Ongeldige geboorteJaar!"));
        }
        if (ranking == null || ranking.isEmpty() || ranking.contains("[a-zA-Z]+") || Integer.parseInt(ranking) < 0) {
            return Optional.of(new InvalidInputException("Ongeldige ranking!"));
        }
        if(geboorteDag == null || geboorteDag < 1 || geboorteDag > 31 || geboorteMaand == null || geboorteMaand < 1 || geboorteMaand > 12) {
            return Optional.of(new IllegalDateException("Ongeldige dag geselecteerd!"));
        }
        if (geboorteMaand == 2 && geboorteDag > 29) {
            return Optional.of(new IllegalDateException("Ongeldige dag geselecteerd!"));
        }
        return Optional.empty();
    }
}
