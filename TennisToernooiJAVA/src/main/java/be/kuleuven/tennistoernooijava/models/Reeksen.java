package be.kuleuven.tennistoernooijava.models;


import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reeksen")
public class Reeksen {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "reeksID", nullable = false)
    private Integer reeksID;

    @Column(name = "reeks", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReeksenWaardes reeks;

    @Column(name = "niveau", nullable = false)
    private Integer niveau;

    @OneToMany(mappedBy = "reeks", fetch = FetchType.LAZY)
    private Set<Matchen> match = new HashSet<>();

    public Integer getReeksID() {
        return reeksID;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public ReeksenWaardes getReeks() {
        return reeks;
    }

    public void setReeks(ReeksenWaardes reeks) {
        this.reeks = reeks;
    }
}
