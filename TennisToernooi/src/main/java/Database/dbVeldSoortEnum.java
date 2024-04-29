package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "VeldSoort_ENUM", schema = "main", catalog = "")
public class dbVeldSoortEnum {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "SOORT")
    private String soort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dbVeldSoortEnum that = (dbVeldSoortEnum) o;
        return id == that.id && Objects.equals(soort, that.soort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, soort);
    }
}
