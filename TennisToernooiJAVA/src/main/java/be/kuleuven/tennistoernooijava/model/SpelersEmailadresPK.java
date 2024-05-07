package be.kuleuven.tennistoernooijava.model;

import java.io.Serializable;
import java.util.Objects;

public class SpelersEmailadresPK implements Serializable {
    private int spelerId;
    private String email;

    // getters and setters

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpelersEmailadresPK that = (SpelersEmailadresPK) o;
        return spelerId == that.spelerId && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, email);
    }
}
