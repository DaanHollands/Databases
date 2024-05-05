package be.kuleuven.tennistoernooijava.database;

import java.io.Serializable;
import java.util.Objects;

public class GeraapteBallenPK implements Serializable {
    private int ballenraperId;
    private int finaleId;

    // getters and setters

    public int getBallenraperId() {
        return ballenraperId;
    }

    public void setBallenraperId(int ballenraperId) {
        this.ballenraperId = ballenraperId;
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
        GeraapteBallenPK that = (GeraapteBallenPK) o;
        return ballenraperId == that.ballenraperId && finaleId == that.finaleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ballenraperId, finaleId);
    }
}
