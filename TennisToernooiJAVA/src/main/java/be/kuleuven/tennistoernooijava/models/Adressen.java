package be.kuleuven.tennistoernooijava.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "adressen")
public class Adressen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adresID", nullable = false)
    private Integer adresID;

    @Column(name = "straatnaam", nullable = false)
    private String straatnaam;

    @Column(name = "straatnummer", nullable = false)
    private Integer straatnummer;

    @Column(name = "postcode", nullable = false)
    private Integer postcode;

    @OneToMany(mappedBy = "clubID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Tennisclubs> tennisclubs = new HashSet<>();

    public Integer getAdresID() {
        return adresID;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public Integer getStraatnummer() {
        return straatnummer;
    }

    public void setStraatnummer(Integer straatnummer) {
        this.straatnummer = straatnummer;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }
}
