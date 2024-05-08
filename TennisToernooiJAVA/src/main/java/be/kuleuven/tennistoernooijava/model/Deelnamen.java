package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Deelnamen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DeelnameID")
    private Integer deelnameID;

    public Integer getDeelnameID() {
        return deelnameID;
    }

    public void setDeelnameID(Integer deelnameId) {
        this.deelnameID = deelnameId;
    }

    @ManyToOne
    @JoinColumn(name = "spelerID", referencedColumnName = "spelerID", nullable = false)
    private Spelers spelerID;

    public Spelers getSpelerID() {
        return spelerID;
    }

    public void setSpelerID(Spelers spelerId) {
        this.spelerID = spelerId;
    }

    @ManyToOne
    @JoinColumn(name = "matchID", referencedColumnName = "matchID", nullable = false)
    private Matchen matchID;

    public Matchen getMatchID() {
        return matchID;
    }

    public void setMatchID(Matchen matchID) {
        this.matchID = matchID;
    }

    @Basic
    @Column(name = "Vraag", nullable = true)
    private String vraag;

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deelnamen deelname = (Deelnamen) o;
        return deelnameID == deelname.deelnameID && spelerID == deelname.spelerID && matchID == deelname.matchID && Objects.equals(vraag, deelname.vraag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deelnameID, spelerID, matchID, vraag);
    }
}
