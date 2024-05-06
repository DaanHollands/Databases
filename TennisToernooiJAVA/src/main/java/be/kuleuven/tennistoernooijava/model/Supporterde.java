package be.kuleuven.tennistoernooijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.IdClass(be.kuleuven.tennistoernooijava.model.SupporterdePK.class)
public class Supporterde {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "SupperterID")
    private int supperterId;

    public int getSupperterId() {
        return supperterId;
    }

    public void setSupperterId(int supperterId) {
        this.supperterId = supperterId;
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
        Supporterde that = (Supporterde) o;
        return supperterId == that.supperterId && finaleId == that.finaleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(supperterId, finaleId);
    }
}
