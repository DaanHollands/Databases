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

    @OneToMany(mappedBy = "matchID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Matchen> matchen = new HashSet<>();

    public void setSpeler(Spelers speler) {
        this.speler = speler;
    }

    public Spelers getSpeler() {
        return speler;
    }

    public void setMatchen(Set<Matchen> matchen) {
        this.matchen = matchen;
    }

    public void addMatch(Matchen match) {
        matchen.add(match);
    }

    public Set<Matchen> getMatchen() {
        return matchen;
    }
}
