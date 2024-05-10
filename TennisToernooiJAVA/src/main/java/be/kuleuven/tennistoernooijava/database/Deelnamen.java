package be.kuleuven.tennistoernooijava.database;

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

    @ManyToOne
    @JoinColumn(name = "spelerID",  referencedColumnName = "spelerID", nullable = false)
    private Spelers datumID;

    @ManyToOne
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

    public Spelers getDatumID() {
        return datumID;
    }

    public void setDatumID(Spelers datumID) {
        this.datumID = datumID;
    }

    public Matchen getMatchID() {
        return matchID;
    }

    public void setMatchID(Matchen matchID) {
        this.matchID = matchID;
    }
}
