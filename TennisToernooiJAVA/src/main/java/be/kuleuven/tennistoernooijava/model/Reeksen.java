package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Reeksen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ReeksID")
    private Integer reeksId;

    public Integer getReeksId() {
        return reeksId;
    }

    public void setReeksId(Integer reeksId) {
        this.reeksId = reeksId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reeksen reeks = (Reeksen) o;
        return reeksId == reeks.reeksId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reeksId);
    }
}
