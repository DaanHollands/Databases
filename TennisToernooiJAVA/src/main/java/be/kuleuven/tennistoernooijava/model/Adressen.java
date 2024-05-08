package be.kuleuven.tennistoernooijava.model;

import javax.persistence.Entity;

import javax.persistence.*;

@Entity
public class Adressen {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AdresID", nullable = false)
    private Integer adresID;

    @Basic
    @Column(name = "Straatnaam", nullable = false)
    private String straatnaam;

    @Basic
    @Column(name = "Straatnummer", nullable = false)
    private Integer straatnummer;

    @Basic
    @Column(name = "Postcode", length = 4, nullable = false)
    private Integer postcode;

    public int getAdresID() {
        return adresID;
    }

    public void setAdresID(int adresID) {
        this.adresID = adresID;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public int getStraatnummer() {
        return straatnummer;
    }

    public void setStraatnummer(int straatnummer) {
        this.straatnummer = straatnummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adressen that = (Adressen) o;

        if (adresID != that.adresID) return false;
        if (straatnummer != that.straatnummer) return false;
        if (postcode != that.postcode) return false;
        if (straatnaam != null ? !straatnaam.equals(that.straatnaam) : that.straatnaam != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adresID;
        result = 31 * result + (straatnaam != null ? straatnaam.hashCode() : 0);
        result = 31 * result + straatnummer;
        result = 31 * result + postcode;
        return result;
    }
}