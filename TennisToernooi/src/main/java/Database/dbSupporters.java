package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Supporters", schema = "main", catalog = "")
public class dbSupporters {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SpelerID")
    private int spelerId;
    @Basic
    @Column(name = "ClubID")
    private int clubId;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbSupporters that = (dbSupporters) o;
        return spelerId == that.spelerId && clubId == that.clubId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, clubId);
    }
}
