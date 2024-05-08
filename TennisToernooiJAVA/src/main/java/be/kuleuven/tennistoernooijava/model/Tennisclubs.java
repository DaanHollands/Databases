package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tennisclubs {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "ClubID")
    private int clubId;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
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

    @OneToOne
    @JoinColumn(name = "adres")
    private Adressen adres;

    public Adressen getAdres() {
        return adres;
    }

    public void setAdres(Adressen adres) {
        this.adres = adres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tennisclubs that = (Tennisclubs) o;
        return clubId == that.clubId && adres == that.adres && Objects.equals(naam, that.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, naam, adres);
    }
}
