package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Wedstrijdleider extends Spelers {

    @OneToMany(mappedBy = "matchID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Matchen> matchen = new HashSet<>();
}
