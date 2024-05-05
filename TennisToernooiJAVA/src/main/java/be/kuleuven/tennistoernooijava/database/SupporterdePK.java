package be.kuleuven.tennistoernooijava.database;

import java.io.Serializable;
import java.util.Objects;

public class SupporterdePK implements Serializable {
    private int supperterId;
    private int finaleId;

    // getters and setters

    public int getSupperterId() {
        return supperterId;
    }

    public void setSupperterId(int supperterId) {
        this.supperterId = supperterId;
    }

    public int getFinaleId() {
        return finaleId;
    }

    public void setFinaleId(int finaleId) {
        this.finaleId = finaleId;
    }

    // equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupporterdePK that = (SupporterdePK) o;
        return supperterId == that.supperterId && finaleId == that.finaleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(supperterId, finaleId);
    }
}
