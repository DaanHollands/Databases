package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Datum {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DatumID")
    private int datumId;
    @Basic
    @Column(name = "Dag")
    private int dag;
    @Basic
    @Column(name = "Maand")
    private int maand;
    @Basic
    @Column(name = "Jaar")
    private int jaar;
    @Basic
    @Column(name = "Uur")
    private Integer uur;
    @Basic
    @Column(name = "Minuten")
    private Integer minuten;

    public int getDatumId() {
        return datumId;
    }

    public void setDatumId(int datumId) {
        this.datumId = datumId;
    }

    public int getDag() {
        return dag;
    }

    public void setDag(int dag) {
        this.dag = dag;
    }

    public int getMaand() {
        return maand;
    }

    public void setMaand(int maand) {
        this.maand = maand;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public Integer getUur() {
        return uur;
    }

    public void setUur(Integer uur) {
        this.uur = uur;
    }

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
        Datum datum = (Datum) o;
        return datumId == datum.datumId && dag == datum.dag && maand == datum.maand && jaar == datum.jaar && Objects.equals(uur, datum.uur) && Objects.equals(minuten, datum.minuten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datumId, dag, maand, jaar, uur, minuten);
    }
}
