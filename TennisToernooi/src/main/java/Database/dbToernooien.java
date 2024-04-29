package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Toernooien", schema = "main", catalog = "")
public class dbToernooien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ToornooiID")
    private int toornooiId;
    @Basic
    @Column(name = "BeginDatumID")
    private int beginDatumId;
    @Basic
    @Column(name = "EindDatumID")
    private int eindDatumId;
    @Basic
    @Column(name = "ClubID")
    private int clubId;

    public int getToornooiId() {
        return toornooiId;
    }

    public void setToornooiId(int toornooiId) {
        this.toornooiId = toornooiId;
    }

    public int getBeginDatumId() {
        return beginDatumId;
    }

    public void setBeginDatumId(int beginDatumId) {
        this.beginDatumId = beginDatumId;
    }

    public int getEindDatumId() {
        return eindDatumId;
    }

    public void setEindDatumId(int eindDatumId) {
        this.eindDatumId = eindDatumId;
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
        dbToernooien that = (dbToernooien) o;
        return toornooiId == that.toornooiId && beginDatumId == that.beginDatumId && eindDatumId == that.eindDatumId && clubId == that.clubId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toornooiId, beginDatumId, eindDatumId, clubId);
    }
}
