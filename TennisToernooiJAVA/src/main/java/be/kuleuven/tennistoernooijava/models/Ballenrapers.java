package be.kuleuven.tennistoernooijava.models;

import be.kuleuven.tennistoernooijava.enums.Posities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
//
@Entity
@Table(name = "ballenrapers")
public class Ballenrapers implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "ballenraperID", referencedColumnName = "spelerID")
    private Spelers speler;

    @Column(name = "plaats", nullable = false)
    @Enumerated(EnumType.STRING)
    private Posities plaats;

    @ManyToMany(mappedBy = "ballenrapers" )
    private Set<Finales> finales = new HashSet<>();

    public Spelers getSpeler() {
        return speler;
    }

    public void setSpeler(Spelers speler) {
        this.speler = speler;
    }

    public Posities getPlaats() {
        return plaats;
    }

    public void setPlaats(Posities plaats) {
        this.plaats = plaats;
    }

    public Set<Finales> getFinales() {
        return finales;
    }

    public void addFinale(Finales finale) {
        finales.add(finale);
    }
}
