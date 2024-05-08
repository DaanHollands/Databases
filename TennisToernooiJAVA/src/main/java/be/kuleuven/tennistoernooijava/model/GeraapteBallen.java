package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GeraapteBallen {

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "ballenraperID")
    private Ballenrapers ballenraperID;

    public Ballenrapers getBallenraperId() {
        return ballenraperID;
    }

    public void setBallenraperId(Ballenrapers ballenraperID) {
        this.ballenraperID = ballenraperID;
    }

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "finaleID")
    private Finales finaleID;

    public Finales getFinaleID() {
        return finaleID;
    }

    public void setFinaleId(Finales finaleId) {
        this.finaleID = finaleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeraapteBallen that = (GeraapteBallen) o;
        return ballenraperID == that.ballenraperID && finaleID == that.finaleID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ballenraperID, finaleID);
    }
}
