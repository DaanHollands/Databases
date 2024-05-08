package be.kuleuven.tennistoernooijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.IdClass(ToernooiReeksPK.class)
public class ToernooiReeksen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "ToernooiID")
    private int toernooiId;

    public int getToernooiId() {
        return toernooiId;
    }

    public void setToernooiId(int toernooiId) {
        this.toernooiId = toernooiId;
    }

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
        ToernooiReeksen that = (ToernooiReeksen) o;
        return toernooiId == that.toernooiId && reeksId == that.reeksId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toernooiId, reeksId);
    }
}
