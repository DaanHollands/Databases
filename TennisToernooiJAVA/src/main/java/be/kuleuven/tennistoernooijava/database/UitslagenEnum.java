package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Uitslagen_ENUM", schema = "main", catalog = "")
public class UitslagenEnum {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UitslagID")
    private int uitslagId;
    @Basic
    @Column(name = "Soort")
    private String soort;

    public int getUitslagId() {
        return uitslagId;
    }

    public void setUitslagId(int uitslagId) {
        this.uitslagId = uitslagId;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UitslagenEnum that = (UitslagenEnum) o;
        return uitslagId == that.uitslagId && Objects.equals(soort, that.soort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uitslagId, soort);
    }
}
