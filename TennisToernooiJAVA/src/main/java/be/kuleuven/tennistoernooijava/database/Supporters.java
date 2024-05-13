package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "supporters")
public class Supporters implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "supporterID", referencedColumnName = "spelerID")
    private Spelers supporterID;

    @ManyToOne
    @JoinColumn(name = "clubID", referencedColumnName = "clubID", nullable = false)
    private Tennisclubs clubID;
//
    @ManyToMany(mappedBy = "supporters", fetch = FetchType.EAGER)
    private Set<Finales> finales = new HashSet<>();
}
