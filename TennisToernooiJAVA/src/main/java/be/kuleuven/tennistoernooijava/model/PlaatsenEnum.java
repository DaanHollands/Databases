package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class PlaatsenEnum {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "PlaatsID")
    private int plaatsId;

    @OneToMany(mappedBy = "plaatsenENUM")
    private Set<Ballenrapers> ballenrapers;

    public Set<Ballenrapers> getBallenrapers() {
        return ballenrapers;
    }

    public void setBallenRaper(Ballenrapers ballenRaper) {
        ballenrapers.add(ballenRaper);
    }

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
