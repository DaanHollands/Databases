package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Wedstrijdleiders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SpelerID")
    private int spelerId;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wedstrijdleiders that = (Wedstrijdleiders) o;
        return spelerId == that.spelerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId);
    }
}
