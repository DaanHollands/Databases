package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Matchen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "MatchID")
    private int matchId;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Basic
    @Column(name = "Uitslag")
    private int uitslag;

    public int getUitslag() {
        return uitslag;
    }

    public void setUitslag(int uitslag) {
        this.uitslag = uitslag;
    }

    @Basic
    @Column(name = "ScoreUit")
    private int scoreUit;

    public int getScoreUit() {
        return scoreUit;
    }

    public void setScoreUit(int scoreUit) {
        this.scoreUit = scoreUit;
    }

    @Basic
    @Column(name = "ScoreThuis")
    private int scoreThuis;

    public int getScoreThuis() {
        return scoreThuis;
    }

    public void setScoreThuis(int scoreThuis) {
        this.scoreThuis = scoreThuis;
    }

    @Basic
    @Column(name = "Datum")
    private int datum;

    public int getDatum() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

    @Basic
    @Column(name = "Wedstrijdleider")
    private int wedstrijdleider;

    public int getWedstrijdleider() {
        return wedstrijdleider;
    }

    public void setWedstrijdleider(int wedstrijdleider) {
        this.wedstrijdleider = wedstrijdleider;
    }

    @Basic
    @Column(name = "Veld")
    private int veld;

    public int getVeld() {
        return veld;
    }

    public void setVeld(int veld) {
        this.veld = veld;
    }

    @Basic
    @Column(name = "ToernooiID")
    private int toernooiId;

    public int getToernooiId() {
        return toernooiId;
    }

    public void setToernooiId(int toernooiId) {
        this.toernooiId = toernooiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matchen match = (Matchen) o;
        return matchId == match.matchId && uitslag == match.uitslag && scoreUit == match.scoreUit && scoreThuis == match.scoreThuis && datum == match.datum && wedstrijdleider == match.wedstrijdleider && veld == match.veld && toernooiId == match.toernooiId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, uitslag, scoreUit, scoreThuis, datum, wedstrijdleider, veld, toernooiId);
    }
}
