package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supporters extends Spelers {

    @ManyToOne
    @JoinColumn(name = "clubID", referencedColumnName = "clubID", nullable = false)
    private Tennisclubs clubID;

    @ManyToMany(mappedBy = "supporters", fetch = FetchType.LAZY)
    private Set<Finales> finales = new HashSet<>();
}
