package be.kuleuven.tennistoernooijava.models;

import java.io.Serializable;
import java.util.Objects;

public class SpelerEmailadressenPK implements Serializable {
    private Spelers spelerID;
    private String email;

    public SpelerEmailadressenPK() {
    }

    public SpelerEmailadressenPK(Spelers spelerID, String email) {
        this.spelerID = spelerID;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpelerEmailadressenPK that = (SpelerEmailadressenPK) o;
        return Objects.equals(spelerID, that.spelerID) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerID, email);
    }
}
