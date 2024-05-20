package be.kuleuven.tennistoernooijava.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tennisclubs")
public class Tennisclubs {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "clubID", nullable = false)
    private Integer clubID;

    @Column(name = "naam", nullable = false, unique = true)
    private String naam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adresID", referencedColumnName = "adresID", nullable = false)
    private Adressen adresID;

    @OneToMany(mappedBy = "tennisclubID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Spelers> spelers = new HashSet<>();

    @OneToMany(mappedBy = "clubOrganistorID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Toernooien> toernooien = new HashSet<>();

    @OneToMany(mappedBy = "tennisclubID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Velden> velden = new HashSet<>();

    @OneToMany(mappedBy = "clubID", cascade = CascadeType.ALL, orphanRemoval = true )
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
