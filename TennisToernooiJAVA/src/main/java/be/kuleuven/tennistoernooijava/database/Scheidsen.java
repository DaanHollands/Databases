package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sheidsen")
public class Scheidsen implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "scheidsID", referencedColumnName = "spelerID")
    private Spelers scheids;

    @Column(name = "arbiterRanking", nullable = true)
    private String arbiterRanking;

    @OneToMany(mappedBy = "matchID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Finales> finales = new HashSet<>();

    public Spelers getScheids() {
        return scheids;
    }

    public void setScheids(Spelers scheids) {
        this.scheids = scheids;
    }

    public String getArbiterRanking() {
        return arbiterRanking;
    }

    public void setArbiterRanking(String arbiterRanking) {
        this.arbiterRanking = arbiterRanking;
    }

    public Set<Finales> getFinales() {
        return finales;
    }

    public void setFinales(Set<Finales> finales) {
        this.finales = finales;
    }
}
