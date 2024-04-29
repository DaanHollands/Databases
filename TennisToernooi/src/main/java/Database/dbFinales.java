package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "Finales", schema = "main", catalog = "")
public class dbFinales {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "MatchID")
    private int matchId;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Basic
    @Column(name = "Scheidsrechter")
    private int scheidsrechter;

    public int getScheidsrechter() {
        return scheidsrechter;
    }

    public void setScheidsrechter(int scheidsrechter) {
        this.scheidsrechter = scheidsrechter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbFinales dbFinales = (dbFinales) o;
        return matchId == dbFinales.matchId && scheidsrechter == dbFinales.scheidsrechter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, scheidsrechter);
    }
}
