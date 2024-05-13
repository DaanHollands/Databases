package be.kuleuven.tennistoernooijava.database;

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

    @OneToMany(mappedBy = "matchID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Matchen> matchen = new HashSet<>();

    public void setSpeler(Spelers speler) {
        this.speler = speler;
    }

    public void setMatchen(Set<Matchen> matchen) {
        this.matchen = matchen;
    }
}
