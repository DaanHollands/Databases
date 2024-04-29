package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "Ballenrapers", schema = "main", catalog = "")
public class dbBallenrapers {
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
    @Column(name = "PlaatsID")
    private int plaatsId;

    public int getPlaatsId() {
        return plaatsId;
    }

    public void setPlaatsId(int plaatsId) {
        this.plaatsId = plaatsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbBallenrapers that = (dbBallenrapers) o;
        return spelerId == that.spelerId && plaatsId == that.plaatsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, plaatsId);
    }
}
