package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ballenrapers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SpelerID")
    private int spelerId;
    @Basic
    @Column(name = "PlaatsID")
    private int plaatsId;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    public int getPlaatsId() {
        return plaatsId;
    }

    public void setPlaatsId(int plaatsId) {
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
