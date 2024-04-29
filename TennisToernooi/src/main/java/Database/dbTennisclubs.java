package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tennisclubs", schema = "main", catalog = "")
public class dbTennisclubs {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClubID")
    private int clubId;
    @Basic
    @Column(name = "Naam")
    private String naam;
    @Basic
    @Column(name = "Adres")
    private int adres;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getAdres() {
        return adres;
    }

    public void setAdres(int adres) {
        this.adres = adres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbTennisclubs that = (dbTennisclubs) o;
        return clubId == that.clubId && adres == that.adres && Objects.equals(naam, that.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, naam, adres);
    }
}
