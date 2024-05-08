package be.kuleuven.tennistoernooijava.model;

import be.kuleuven.tennistoernooijava.enums.Plaatsen;
import net.bytebuddy.description.field.FieldList;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Ballenrapers {

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "SpelerID")
    private Spelers spelerId;

    @ManyToOne
    @JoinColumn(name = "plaatsID", nullable = false)
    private PlaatsenEnum plaatsId;

    @ManyToMany
    @JoinColumn(name = "finales", nullable = false)
    private Set<Finales> finales;

    public Set<Finales> getFinales() {
        return finales;
    }

    public void addFinale(Finales finale) {
        finales.add(finale);
    }

    public Spelers getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(Spelers spelerId) {
        this.spelerId = spelerId;
    }

    public PlaatsenEnum getPlaatsId() {
        return plaatsId;
    }

    public void setPlaatsId(PlaatsenEnum plaatsId) {
        this.plaatsId = plaatsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ballenrapers that = (Ballenrapers) o;
        return spelerId == that.spelerId && plaatsId == that.plaatsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, plaatsId);
    }
}
