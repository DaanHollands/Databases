package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "Plaatsen_ENUM", schema = "main", catalog = "")
public class PlaatsenEnum {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "PlaatsID")
    private int plaatsId;

    public int getPlaatsId() {
        return plaatsId;
    }

    public void setPlaatsId(int plaatsId) {
        this.plaatsId = plaatsId;
    }

    @Basic
    @Column(name = "Plaats")
    private String plaats;

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaatsenEnum that = (PlaatsenEnum) o;
        return plaatsId == that.plaatsId && Objects.equals(plaats, that.plaats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plaatsId, plaats);
    }
}
