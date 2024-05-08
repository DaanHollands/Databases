package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Datums {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DatumID", nullable = false)
    private Integer datumId;

    @Basic
    @Column(name = "Dag", nullable = false)
    private Integer dag;

    @Basic
    @Column(name = "Maand", nullable = false)
    private Integer maand;

    @Basic
    @Column(name = "Jaar" , nullable = false)
    private Integer jaar;

    @Basic
    @Column(name = "Uur" , nullable = true)
    private Integer uur;

    @Basic
    @Column(name = "Minuten", nullable = true)
    private Integer minuten;

    @OneToMany
    @JoinColumn(name = "matches")
    private Set<Matchen> matches;

    public Set<Matchen> getMatches() {
        return matches;
    }

    public void setMatches(Matchen matche) {
        matches.add(matche);
    }

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
        Datums datum = (Datums) o;
        return datumId == datum.datumId && dag == datum.dag && maand == datum.maand && jaar == datum.jaar && Objects.equals(uur, datum.uur) && Objects.equals(minuten, datum.minuten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datumId, dag, maand, jaar, uur, minuten);
    }
}
