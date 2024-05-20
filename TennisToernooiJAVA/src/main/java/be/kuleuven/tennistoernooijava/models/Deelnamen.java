package be.kuleuven.tennistoernooijava.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "deelnamen")
public class Deelnamen {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
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

    public void setSpelerID(Spelers speler) {
        this.spelerID = speler;
    }

    public Matchen getMatchID() {
        return matchID;
    }

    public void setMatchID(Matchen matchID) {
        this.matchID = matchID;
    }
}
