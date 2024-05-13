package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Finales")
public class Finales extends Matchen {
//
    @ManyToOne
    @JoinColumn(name = "scheidsID",  referencedColumnName = "scheidsID", nullable = false)
    private Scheidsen scheidsID;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "supporterde",
            joinColumns = {
                    @JoinColumn(name = "finaleID", referencedColumnName = "matchID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "supporterID", referencedColumnName = "supporterID"),
            }
    )
    private Set<Supporters> supporters = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "geraapteBallen",
            joinColumns = {
                    @JoinColumn(name = "finaleID", referencedColumnName = "matchID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ballenraperID", referencedColumnName = "ballenraperID"),
            }
    )
    private Set<Ballenrapers> ballenrapers = new HashSet<>();

    public Scheidsen getScheidsID() {
        return scheidsID;
    }

    public void setScheidsID(Scheidsen scheidsID) {
        this.scheidsID = scheidsID;
    }
}


