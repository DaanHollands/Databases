package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Toernooi {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "ToornooiID")
    private int toornooiId;

    public int getToornooiId() {
        return toornooiId;
    }

    public void setToornooiId(int toornooiId) {
        this.toornooiId = toornooiId;
    }

    @Basic
    @Column(name = "BeginDatumID")
    private int beginDatumId;

    public int getBeginDatumId() {
        return beginDatumId;
    }

    public void setBeginDatumId(int beginDatumId) {
        this.beginDatumId = beginDatumId;
    }

    @Basic
    @Column(name = "EindDatumID")
    private int eindDatumId;

    public int getEindDatumId() {
        return eindDatumId;
    }

    public void setEindDatumId(int eindDatumId) {
        this.eindDatumId = eindDatumId;
    }

    @Basic
    @Column(name = "ClubID")
    private int clubId;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toernooi that = (Toernooi) o;
        return toornooiId == that.toornooiId && beginDatumId == that.beginDatumId && eindDatumId == that.eindDatumId && clubId == that.clubId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toornooiId, beginDatumId, eindDatumId, clubId);
    }
}
