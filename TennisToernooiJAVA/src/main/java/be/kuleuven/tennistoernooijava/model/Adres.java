package be.kuleuven.tennistoernooijava.model;

import javax.persistence.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Adressen", schema = "main", catalog = "")
public class Adres {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AdresID")
    private int adresId;
    @Basic
    @Column(name = "Straatnaam")
    private String straatnaam;
    @Basic
    @Column(name = "Straatnummer")
    private int straatnummer;
    @Basic
    @Column(name = "Postcode")
    private int postcode;

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
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

        Adres that = (Adres) o;

        if (adresId != that.adresId) return false;
        if (straatnummer != that.straatnummer) return false;
        if (postcode != that.postcode) return false;
        if (straatnaam != null ? !straatnaam.equals(that.straatnaam) : that.straatnaam != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adresId;
        result = 31 * result + (straatnaam != null ? straatnaam.hashCode() : 0);
        result = 31 * result + straatnummer;
        result = 31 * result + postcode;
        return result;
    }
}