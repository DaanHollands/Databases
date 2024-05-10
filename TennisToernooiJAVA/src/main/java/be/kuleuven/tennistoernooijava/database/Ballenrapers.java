package be.kuleuven.tennistoernooijava.database;

import be.kuleuven.tennistoernooijava.enums.Plaatsen;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ballenrapers extends Spelers {

    @Column(name = "plaats", nullable = false)
    @Enumerated(EnumType.STRING)
    private Plaatsen plaats;

    @ManyToMany(mappedBy = "ballenrapers", fetch = FetchType.LAZY)
    private Set<Finales> finales = new HashSet<>();


}
