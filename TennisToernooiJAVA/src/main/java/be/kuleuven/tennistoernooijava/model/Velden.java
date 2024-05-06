package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Velden {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "VeldID")
    private int veldId;
    @Basic
    @Column(name = "VeldSoort")
    private int veldSoort;
    @Basic
    @Column(name = "ClubID")
    private int clubId;

    public int getVeldId() {
        return veldId;
    }

    public void setVeldId(int veldId) {
        this.veldId = veldId;
    }

    public int getVeldSoort() {
        return veldSoort;
    }

    public void setVeldSoort(int veldSoort) {
        this.veldSoort = veldSoort;
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
        Velden velden = (Velden) o;
        return veldId == velden.veldId && veldSoort == velden.veldSoort && clubId == velden.clubId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(veldId, veldSoort, clubId);
    }
}
