package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Scheids {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "SpelerID")
    private int spelerId;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    @Basic
    @Column(name = "Arbiter-Ranking")
    private Integer arbiterRanking;

    public Integer getArbiterRanking() {
        return arbiterRanking;
    }

    public void setArbiterRanking(Integer arbiterRanking) {
        this.arbiterRanking = arbiterRanking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheids scheids = (Scheids) o;
        return spelerId == scheids.spelerId && Objects.equals(arbiterRanking, scheids.arbiterRanking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, arbiterRanking);
    }
}
