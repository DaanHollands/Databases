package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "Spelers")
public class Spelers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "SpelerID")
    private int spelerId;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    @Basic
    @Column(name = "Naam")
    private String naam;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Basic
    @Column(name = "Telefoonnummer")
    private String telefoonnummer;

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    @Basic
    @Column(name = "Geboortedatum")
    private int geboortedatum;

    public int getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(int geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Basic
    @Column(name = "Gewicht")
    private BigInteger gewicht;

    public BigInteger getGewicht() {
        return gewicht;
    }

    public void setGewicht(BigInteger gewicht) {
        this.gewicht = gewicht;
    }

    @Basic
    @Column(name = "Lengte")
    private BigInteger lengte;

    public BigInteger getLengte() {
        return lengte;
    }

    public void setLengte(BigInteger lengte) {
        this.lengte = lengte;
    }

    @Basic
    @Column(name = "Ranking")
    private Integer ranking;

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    @Basic
    @Column(name = "Geslacht")
    private String geslacht;

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    @Basic
    @Column(name = "Reeks")
    private int reeks;

    public int getReeks() {
        return reeks;
    }

    public void setReeks(int reeks) {
        this.reeks = reeks;
    }

    @Basic
    @Column(name = "Club")
    private int club;

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spelers speler = (Spelers) o;
        return spelerId == speler.spelerId && geboortedatum == speler.geboortedatum && reeks == speler.reeks && club == speler.club && Objects.equals(naam, speler.naam) && Objects.equals(telefoonnummer, speler.telefoonnummer) && Objects.equals(gewicht, speler.gewicht) && Objects.equals(lengte, speler.lengte) && Objects.equals(ranking, speler.ranking) && Objects.equals(geslacht, speler.geslacht);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, naam, telefoonnummer, geboortedatum, gewicht, lengte, ranking, geslacht, reeks, club);
    }
}
