package be.kuleuven.tennistoernooijava.database;

import be.kuleuven.tennistoernooijava.enums.Plaatsen;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
//
@Entity
@Table(name = "ballenrapers")
public class Ballenrapers implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "ballenraperID", referencedColumnName = "spelerID")
    private Spelers speler;

    @Column(name = "plaats", nullable = false)
    @Enumerated(EnumType.STRING)
    private Plaatsen plaats;

    @ManyToMany(mappedBy = "ballenrapers", fetch = FetchType.EAGER)
    private Set<Finales> finales = new HashSet<>();


}
