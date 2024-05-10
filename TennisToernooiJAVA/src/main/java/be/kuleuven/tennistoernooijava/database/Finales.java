package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Finales")
public class Finales extends Matchen {

    @ManyToOne
    @JoinColumn(name = "spelerID",  referencedColumnName = "spelerID", nullable = false)
    private Scheidsen scheidsID;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "supporterde",
            joinColumns = {
                    @JoinColumn(name = "finaleID", referencedColumnName = "matchID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "supporterID", referencedColumnName = "spelerID"),
            }
    )
    private Set<Supporters> supporters = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "geraapteBallen",
            joinColumns = {
                    @JoinColumn(name = "finaleID", referencedColumnName = "matchID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "supporterID", referencedColumnName = "spelerID"),
            }
    )
    private Set<Ballenrapers> ballenrapers = new HashSet<>();
}
