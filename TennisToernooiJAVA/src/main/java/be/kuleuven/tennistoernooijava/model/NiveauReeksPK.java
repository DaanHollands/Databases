package be.kuleuven.tennistoernooijava.model;

import java.io.Serializable;
import java.util.Objects;

public class NiveauReeksPK implements Serializable {
    private int reeksId;
    private String niveau;

    // getters and setters

    public int getReeksId() {
        return reeksId;
    }

    public void setReeksId(int reeksId) {
        this.reeksId = reeksId;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    // equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NiveauReeksPK that = (NiveauReeksPK) o;
        return reeksId == that.reeksId && Objects.equals(niveau, that.niveau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reeksId, niveau);
    }
}
