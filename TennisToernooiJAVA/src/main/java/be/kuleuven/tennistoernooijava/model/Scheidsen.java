package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Scheidsen {

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "spelerID")
    private Spelers spelerId;

    public Spelers getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(Spelers spelerId) {
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
        Scheidsen scheids = (Scheidsen) o;
        return spelerId == scheids.spelerId && Objects.equals(arbiterRanking, scheids.arbiterRanking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, arbiterRanking);
    }
}
