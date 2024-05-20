package be.kuleuven.tennistoernooijava.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "datums")
public class Datums {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "datumID", nullable = false)
    private Integer datumID;

    @Column(name = "dag", nullable = false)
    private Integer dag;

    @Column(name = "maand", nullable = false)
    private Integer maand;

    @Column(name = "jaar", nullable = false)
    private Integer jaar;

    @Column(name = "uur", nullable = true)
    private Integer uur;

    @Column(name = "minuten", nullable = true)
    private Integer minuten;

    @OneToMany(mappedBy = "datumID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Spelers> spelers = new HashSet<>();

    public Integer getDag() {
        return dag;
    }

    public void setDag(Integer dag) {
        this.dag = dag;
    }

    public Integer getDatumID() {
        return datumID;
    }


    public Integer getMaand() {
        return maand;
    }

    public void setMaand(Integer maand) {
        this.maand = maand;
    }

    public Integer getJaar() {
        return jaar;
    }

    public void setJaar(Integer jaar) {
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
}
