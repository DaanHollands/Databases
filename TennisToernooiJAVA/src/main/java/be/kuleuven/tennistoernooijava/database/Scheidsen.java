package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Scheidsen extends Spelers{

    @Column(name = "arbiterRanking", nullable = true)
    private String arbiterRanking;

    @OneToMany(mappedBy = "matchID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Finales> finales = new HashSet<>();

}
