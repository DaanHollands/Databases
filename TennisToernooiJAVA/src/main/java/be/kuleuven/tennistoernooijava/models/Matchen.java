package be.kuleuven.tennistoernooijava.models;

import be.kuleuven.tennistoernooijava.enums.Uitslagen;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "matchen")
public class Matchen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "matchID", nullable = false)
    private Integer matchID;

    @Column(name = "uitslag")
    @Enumerated(EnumType.STRING)
    private Uitslagen uitslag;

    @Column(name = "scoreuit")
    private Integer scoreuit;

    @Column(name = "scorethuis")
    private Integer scorethus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "datumID",  referencedColumnName = "datumID", nullable = false)
    private Datums datumID;

    @OneToMany(mappedBy = "deelnameID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Deelnamen> deelnamens = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toernooiID", referencedColumnName = "toernooiID", nullable = false)
    private Toernooien toernooiID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veldID", referencedColumnName = "veldID", nullable = false)
    private Velden veldID;

    public Velden getVeldID() {
        return veldID;
    }

    public void setVeldID(Velden veldID) {
        this.veldID = veldID;
    }

    public Toernooien getToernooiID() {
        return toernooiID;
    }

    public void setToernooiID(Toernooien toernooiID) {
        this.toernooiID = toernooiID;
    }

    public Integer getMatchID() {
        return matchID;
    }

    public Uitslagen getUitslag() {
        return uitslag;
    }

    public void setUitslag(Uitslagen uitslag) {
        this.uitslag = uitslag;
    }

    public Integer getScoreuit() {
        return scoreuit;
    }

    public void setScoreuit(Integer scoreuit) {
        this.scoreuit = scoreuit;
    }

    public Integer getScorethus() {
        return scorethus;
    }

    public void setScorethus(Integer scorethus) {
        this.scorethus = scorethus;
    }

    public Datums getDatumID() {
        return datumID;
    }

    public void setDatumID(Datums datumID) {
        this.datumID = datumID;
    }

    public Set<Deelnamen> getDeelnamens() {
        return deelnamens;
    }

    public void addDeelname(Deelnamen deelnamen) {
        deelnamens.add(deelnamen);
    }
}
