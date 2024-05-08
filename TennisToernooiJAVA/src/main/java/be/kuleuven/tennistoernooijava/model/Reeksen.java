package be.kuleuven.tennistoernooijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Reeksen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "ReeksID")
    private int reeksId;

    public int getReeksId() {
        return reeksId;
    }

    public void setReeksId(int reeksId) {
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
