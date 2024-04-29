package Database;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "Spelers", schema = "main", catalog = "")
public class dbSpelers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SpelerID")
    private int spelerId;
    @Basic
    @Column(name = "Naam")
    private String naam;
    @Basic
    @Column(name = "Telefoonnummer")
    private String telefoonnummer;
    @Basic
    @Column(name = "Geboortedatum")
    private int geboortedatum;
    @Basic
    @Column(name = "Gewicht")
    private BigInteger gewicht;
    @Basic
    @Column(name = "Lengte")
    private BigInteger lengte;
    @Basic
    @Column(name = "Ranking")
    private Integer ranking;
    @Basic
    @Column(name = "Geslacht")
    private String geslacht;
    @Basic
    @Column(name = "Reeks")
    private int reeks;
    @Basic
    @Column(name = "Club")
    private int club;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public int getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(int geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public BigInteger getGewicht() {
        return gewicht;
    }

    public void setGewicht(BigInteger gewicht) {
        this.gewicht = gewicht;
    }

    public BigInteger getLengte() {
        return lengte;
    }

    public void setLengte(BigInteger lengte) {
        this.lengte = lengte;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public int getReeks() {
        return reeks;
    }

    public void setReeks(int reeks) {
        this.reeks = reeks;
    }

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
        dbSpelers dbSpelers = (dbSpelers) o;
        return spelerId == dbSpelers.spelerId && geboortedatum == dbSpelers.geboortedatum && reeks == dbSpelers.reeks && club == dbSpelers.club && Objects.equals(naam, dbSpelers.naam) && Objects.equals(telefoonnummer, dbSpelers.telefoonnummer) && Objects.equals(gewicht, dbSpelers.gewicht) && Objects.equals(lengte, dbSpelers.lengte) && Objects.equals(ranking, dbSpelers.ranking) && Objects.equals(geslacht, dbSpelers.geslacht);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, naam, telefoonnummer, geboortedatum, gewicht, lengte, ranking, geslacht, reeks, club);
    }
}
