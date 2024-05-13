package be.kuleuven.tennistoernooijava.models;

import java.io.Serializable;
import java.util.Objects;

public class ReeksenPK implements Serializable {
    private String niveau;
    private Integer reeksID;

    public ReeksenPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReeksenPK that = (ReeksenPK) o;
        return Objects.equals(niveau, that.niveau) && Objects.equals(reeksID, that.reeksID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(niveau, reeksID);
    }
}
