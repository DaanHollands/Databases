package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tennisclubs")
public class Tennisclubs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clubID", nullable = false)
    private Integer clubID;

    @Column(name = "naam", nullable = false)
    private String naam;

    @ManyToOne
    @JoinColumn(name = "adresID", referencedColumnName = "adresID", nullable = false)
    private Adressen adresID;

    @OneToMany(mappedBy = "spelerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Spelers> spelers = new HashSet<>();

    @OneToMany(mappedBy = "toernooiID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Toernooien> toernooienID = new HashSet<>();

    @OneToMany(mappedBy = "veldID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Velden> velden = new HashSet<>();

    @OneToMany(mappedBy = "spelerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Supporters> supporters = new HashSet<>();

    public Integer getClubID() {
        return clubID;
    }

    public String getNaam() {
        return naam;
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
