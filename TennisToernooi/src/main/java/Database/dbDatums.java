package Database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "Datums", schema = "main", catalog = "")
public class dbDatums {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "DatumID")
    private int datumId;

    public int getDatumId() {
        return datumId;
    }

    public void setDatumId(int datumId) {
        this.datumId = datumId;
    }

    @Basic
    @Column(name = "Dag")
    private int dag;

    public int getDag() {
        return dag;
    }

    public void setDag(int dag) {
        this.dag = dag;
    }

    @Basic
    @Column(name = "Maand")
    private int maand;

    public int getMaand() {
        return maand;
    }

    public void setMaand(int maand) {
        this.maand = maand;
    }

    @Basic
    @Column(name = "Jaar")
    private int jaar;

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    @Basic
    @Column(name = "Uur")
    private Integer uur;

    public Integer getUur() {
        return uur;
    }

    public void setUur(Integer uur) {
        this.uur = uur;
    }

    @Basic
    @Column(name = "Minuten")
    private Integer minuten;

    public Integer getMinuten() {
        return minuten;
    }

    public void setMinuten(Integer minuten) {
        this.minuten = minuten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbDatums dbDatums = (dbDatums) o;
        return datumId == dbDatums.datumId && dag == dbDatums.dag && maand == dbDatums.maand && jaar == dbDatums.jaar && Objects.equals(uur, dbDatums.uur) && Objects.equals(minuten, dbDatums.minuten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datumId, dag, maand, jaar, uur, minuten);
    }
}
