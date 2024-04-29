package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "Deelnamen", schema = "main", catalog = "")
public class dbDeelnamen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "DeelnameID")
    private int deelnameId;

    public int getDeelnameId() {
        return deelnameId;
    }

    public void setDeelnameId(int deelnameId) {
        this.deelnameId = deelnameId;
    }

    @Basic
    @Column(name = "SpelerID")
    private int spelerId;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    @Basic
    @Column(name = "MatchID")
    private int matchId;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Basic
    @Column(name = "Vraag")
    private String vraag;

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbDeelnamen that = (dbDeelnamen) o;
        return deelnameId == that.deelnameId && spelerId == that.spelerId && matchId == that.matchId && Objects.equals(vraag, that.vraag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deelnameId, spelerId, matchId, vraag);
    }
}
