package be.kuleuven.tennistoernooijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.IdClass(GeraapteBalPK.class)
public class GeraapteBal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "BallenraperID")
    private int ballenraperId;

    public int getBallenraperId() {
        return ballenraperId;
    }

    public void setBallenraperId(int ballenraperId) {
        this.ballenraperId = ballenraperId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "FinaleID")
    private int finaleId;

    public int getFinaleId() {
        return finaleId;
    }

    public void setFinaleId(int finaleId) {
        this.finaleId = finaleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeraapteBal that = (GeraapteBal) o;
        return ballenraperId == that.ballenraperId && finaleId == that.finaleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ballenraperId, finaleId);
    }
}
