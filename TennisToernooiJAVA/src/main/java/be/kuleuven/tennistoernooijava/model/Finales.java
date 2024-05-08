package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Finales {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MatchID")
    private Integer matchId;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    @ManyToMany
    @JoinColumn(name="scheidsrechter", referencedColumnName = "spelerID", nullable = false)
    private Scheidsen scheidsrechter;

    public Scheidsen getScheidsrechters() {
        return scheidsrechter;
    }

    public void setScheidsrecht(Scheidsen scheidsrechter) {
        this.scheidsrechter = scheidsrechter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Finales finale = (Finales) o;
        return matchId == finale.matchId && scheidsrechter == finale.scheidsrechter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, scheidsrechter);
    }
}
