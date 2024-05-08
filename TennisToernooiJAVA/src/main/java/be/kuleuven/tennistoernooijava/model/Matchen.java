package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Matchen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "matchID")
    private Integer matchID;

    public Integer getMatchID() {
        return matchID;
    }

    public void setMatchID(Integer matchId) {
        this.matchID = matchId;
    }

    @ManyToOne
    @JoinColumn(name = "uitslagID", nullable = false)
    private UitslagenEnum uitslag;

    public UitslagenEnum getUitslag() {
        return uitslag;
    }

    public void setUitslag(UitslagenEnum uitslag) {
        this.uitslag = uitslag;
    }

    @Basic
    @Column(name = "ScoreUit")
    private Integer scoreUit;

    public Integer getScoreUit() {
        return scoreUit;
    }

    public void setScoreUit(Integer scoreUit) {
        this.scoreUit = scoreUit;
    }

    @Basic
    @Column(name = "ScoreThuis")
    private Integer scoreThuis;

    public Integer getScoreThuis() {
        return scoreThuis;
    }

    public void setScoreThuis(Integer scoreThuis) {
        this.scoreThuis = scoreThuis;
    }

    @ManyToOne
    @JoinColumn(name = "datumID", nullable = false)
    private Datums datum;

    public Datums getDatum() {
        return datum;
    }

    public void setDatum(Datums datum) {
        this.datum = datum;
    }

    @ManyToOne
    @JoinColumn(name = "wedstrijdleiderID", nullable = false)
    private Wedstrijdleiders wedstrijdleider;

    public Wedstrijdleiders getWedstrijdleider() {
        return wedstrijdleider;
    }

    public void setWedstrijdleider(Wedstrijdleiders wedstrijdleider) {
        this.wedstrijdleider = wedstrijdleider;
    }

    @ManyToOne
    @JoinColumn(name = "veldID", nullable = false)
    private VeldSoortEnum veld;

    public VeldSoortEnum getVeld() {
        return veld;
    }

    public void setVeld(VeldSoortEnum veld) {
        this.veld = veld;
    }

    @ManyToOne
    @JoinColumn(name = "toernooiID", nullable = false)
    private Toernooien toernooiId;

    public Toernooien getToernooiId() {
        return toernooiId;
    }

    public void setToernooiId(Toernooien toernooiId) {
        this.toernooiId = toernooiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matchen match = (Matchen) o;
        return matchID == match.matchID && uitslag == match.uitslag && scoreUit == match.scoreUit && scoreThuis == match.scoreThuis && datum == match.datum && wedstrijdleider == match.wedstrijdleider && veld == match.veld && toernooiId == match.toernooiId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchID, uitslag, scoreUit, scoreThuis, datum, wedstrijdleider, veld, toernooiId);
    }
}
