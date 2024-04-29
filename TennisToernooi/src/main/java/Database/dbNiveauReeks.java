package Database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "NiveauReeks", schema = "main", catalog = "")
@javax.persistence.IdClass(Database.dbNiveauReeksPK.class)
public class dbNiveauReeks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "ReeksID")
    private int reeksId;

    public int getReeksId() {
        return reeksId;
    }

    public void setReeksId(int reeksId) {
        this.reeksId = reeksId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "Niveau")
    private String niveau;

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbNiveauReeks that = (dbNiveauReeks) o;
        return reeksId == that.reeksId && Objects.equals(niveau, that.niveau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reeksId, niveau);
    }
}
