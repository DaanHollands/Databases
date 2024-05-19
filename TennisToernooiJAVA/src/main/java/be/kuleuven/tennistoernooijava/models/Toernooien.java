package be.kuleuven.tennistoernooijava.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "toernooien")
public class Toernooien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "toernooiID", nullable = false)
    private Integer toernooiID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beginDatumID", referencedColumnName = "datumID", nullable = false)
    private Datums beginDatumID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eindDatumID", referencedColumnName = "datumID", nullable = false)
    private Datums eindDatumID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clubOrganisatorID", referencedColumnName = "clubID", nullable = false)
    private Tennisclubs clubOrganistorID;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wedstrijdLeider",  referencedColumnName = "wedstrijdleiderID", nullable = false)
    private Wedstrijdleider wedstrijdleider;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "toernooienReeksen",
            joinColumns = {
                @JoinColumn(name = "toernooiID", referencedColumnName = "toernooiID", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "reeksID", referencedColumnName = "reeksID", nullable = false),
                    @JoinColumn(name = "niveau", referencedColumnName = "niveau", nullable = false)
            }
    )
    private Set<Reeksen> reeksen = new HashSet<>();

    @OneToMany(mappedBy = "matchID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Matchen> matchen = new HashSet<>();

    public void addReeks(Reeksen reeks) {
        reeksen.add(reeks);
    }

    public Set<Reeksen> getReeksen() {
        return reeksen;
    }

    public Integer getToernooiID() {
        return toernooiID;
    }

    public Set<Matchen> getMatchen() {
        return matchen;
    }

    public void addMatchen(Matchen match) {
        matchen.add(match);
    }

    public Datums getBeginDatumID() {
        return beginDatumID;
    }

    public void setBeginDatumID(Datums beginDatumID) {
        this.beginDatumID = beginDatumID;
    }

    public Datums getEindDatumID() {
        return eindDatumID;
    }

    public void setEindDatumID(Datums eindDatumID) {
        this.eindDatumID = eindDatumID;
    }

    public Tennisclubs getClubOrganistorID() {
        return clubOrganistorID;
    }

    public void setClubOrganistorID(Tennisclubs clubOrganistorID) {
        this.clubOrganistorID = clubOrganistorID;
    }

    public Wedstrijdleider getWedstrijdleider() {
        return wedstrijdleider;
    }

    public void setWedstrijdleider(Wedstrijdleider wedstrijdleider) {
        this.wedstrijdleider = wedstrijdleider;
    }
}


