package be.kuleuven.tennistoernooijava.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wedstrijdleider")
public class Wedstrijdleider implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "wedstrijdleiderID", referencedColumnName = "spelerID")
    private Spelers speler;

    @OneToOne(mappedBy = "wedstrijdleider", cascade = CascadeType.ALL)
    @JoinColumn(name = "toernooiID", referencedColumnName = "toernooiID", nullable = true)
    private Toernooien toernooi;

    public void setSpeler(Spelers speler) {
        this.speler = speler;
    }

    public Spelers getSpeler() {
        return speler;
    }

    public Toernooien getToernooi() {
        return toernooi;
    }

    public void setToernooi(Toernooien toernooi) {
        this.toernooi = toernooi;
    }
}
