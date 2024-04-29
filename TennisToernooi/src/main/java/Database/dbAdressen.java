package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "Adressen", schema = "main", catalog = "")
public class dbAdressen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "AdresID")
    private int adresId;

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
    }

    @Basic
    @Column(name = "Straatnaam")
    private String straatnaam;

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    @Basic
    @Column(name = "Straatnummer")
    private int straatnummer;

    public int getStraatnummer() {
        return straatnummer;
    }

    public void setStraatnummer(int straatnummer) {
        this.straatnummer = straatnummer;
    }

    @Basic
    @Column(name = "Postcode")
    private int postcode;

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
        dbAdressen that = (dbAdressen) o;
        return adresId == that.adresId && straatnummer == that.straatnummer && postcode == that.postcode && Objects.equals(straatnaam, that.straatnaam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresId, straatnaam, straatnummer, postcode);
    }
}
