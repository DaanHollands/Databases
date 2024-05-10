package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "toernooien")
public class Toernooien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toernooiID", nullable = false)
    private Integer toernooiID;

    @ManyToOne
    @JoinColumn(name = "beginDatumID", referencedColumnName = "datumID", nullable = false)
    private Datums beginDatumID;

    @ManyToOne
    @JoinColumn(name = "eindDatumID", referencedColumnName = "datumID", nullable = false)
    private Datums eindDatumID;

    @ManyToOne
    @JoinColumn(name = "clubOrganisatorID", referencedColumnName = "clubID", nullable = false)
    private Tennisclubs clubOrganistorID;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    @OneToMany(mappedBy = "matchID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Matchen> matchen = new HashSet<>();

    public Integer getToernooiID() {
        return toernooiID;
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
}
