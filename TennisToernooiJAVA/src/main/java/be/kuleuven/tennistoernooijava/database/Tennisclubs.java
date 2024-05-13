package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tennisclubs")
public class Tennisclubs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clubID", nullable = false)
    private Integer clubID;

    @Column(name = "naam", nullable = false, unique = true)
    private String naam;

    @ManyToOne
    @JoinColumn(name = "adresID", referencedColumnName = "adresID", nullable = false)
    private Adressen adresID;

    @OneToMany(mappedBy = "spelerID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Spelers> spelers = new HashSet<>();

    @OneToMany(mappedBy = "toernooiID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Toernooien> toernooien = new HashSet<>();

    @OneToMany(mappedBy = "veldID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Velden> velden = new HashSet<>();

    @OneToMany(mappedBy = "supporterID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Supporters> supporters = new HashSet<>();

    public Set<Toernooien> getToernooien() {
        return toernooien;
    }

    public void addToernooi(Toernooien toernooi) {
        toernooien.add(toernooi);
    }

    public Integer getClubID() {
        return clubID;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Velden> getVelden() {
        return velden;
    }

    public void addVeld(Velden veld) {
        velden.add(veld);
    }

    public Set<Spelers> getSpelers() {
        return spelers;
    }

    public void addSpeler(Spelers speler) {
        spelers.add(speler);
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Adressen getAdresID() {
        return adresID;
    }

    public void setAdresID(Adressen adresID) {
        this.adresID = adresID;
    }
}
