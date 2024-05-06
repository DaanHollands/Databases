package be.kuleuven.tennistoernooijava.model;

import java.io.Serializable;
import java.util.Objects;

public class ToernooienReeksenPK implements Serializable {
    private int toernooiId;
    private int reeksId;

    // getters and setters

    public int getToernooiId() {
        return toernooiId;
    }

    public void setToernooiId(int toernooiId) {
        this.toernooiId = toernooiId;
    }

    public int getReeksId() {
        return reeksId;
    }

    public void setReeksId(int reeksId) {
        this.reeksId = reeksId;
    }

    // equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToernooienReeksenPK that = (ToernooienReeksenPK) o;
        return toernooiId == that.toernooiId && reeksId == that.reeksId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toernooiId, reeksId);
    }
}
