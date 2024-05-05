package be.kuleuven.tennistoernooijava.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.IdClass(be.kuleuven.tennistoernooijava.database.ToernooienReeksenPK.class)
public class ToernooienReeksen {
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
        ToernooienReeksen that = (ToernooienReeksen) o;
        return toernooiId == that.toernooiId && reeksId == that.reeksId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toernooiId, reeksId);
    }
}
