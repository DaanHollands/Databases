package be.kuleuven.tennistoernooi.models.CreatePlayer;

public class CreatePlayer {
    private String naam;
    private String achterNaam;
    private String email;
    private String telefoonNummer;
    private String geslacht;
    private int gewicht;
    private int lengte;
    private int geboorteDag;
    private int geboorteMaand;
    private int geboorteJaar;
    private int ranking;

    public CreatePlayer(String naam, String achterNaam, String email, String telefoonNummer, String geslacht, int gewicht, int lengte, int geboorteDag, int geboorteMaand, int geboorteJaar, int ranking) {
        this.naam = naam;
        this.achterNaam = achterNaam;
        this.email = email;
        this.telefoonNummer = telefoonNummer;
        this.geslacht = geslacht;
        this.gewicht = gewicht;
        this.lengte = lengte;
        this.geboorteDag = geboorteDag;
        this.geboorteMaand = geboorteMaand;
        this.geboorteJaar = geboorteJaar;
        this.ranking = ranking;
    }
}
