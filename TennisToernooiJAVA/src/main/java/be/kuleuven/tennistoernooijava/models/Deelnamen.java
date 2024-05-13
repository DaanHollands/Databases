package be.kuleuven.tennistoernooijava.models;

import javax.persistence.*;

@Entity
@Table(name = "deelnamen")
public class Deelnamen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deelnameID", nullable = false)
    private Integer deelnameID;

    @Column(name = "vraag", nullable = true)
    private String vraag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "spelerID",  referencedColumnName = "spelerID", nullable = false)
    private Spelers spelerID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matchID",  referencedColumnName = "matchID", nullable = false)
    private Matchen matchID;

    public Integer getDeelnameID() {
        return deelnameID;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    public Spelers getSpelerID() {
        return spelerID;
    }

    public void setSpelerID(Spelers datumID) {
        this.spelerID = datumID;
    }

    public Matchen getMatchID() {
        return matchID;
    }

    public void setMatchID(Matchen matchID) {
        this.matchID = matchID;
    }
}
