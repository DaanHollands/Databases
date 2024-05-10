package be.kuleuven.tennistoernooijava.database;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reeksen")
@IdClass(ReeksenPK.class)
public class Reeksen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reeksID", nullable = false)
    private Integer reeksID;

    @Id
    @Column(name = "niveau", nullable = false)
    private String niveau;

    @OneToMany(mappedBy = "reeksID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Spelers> spelers = new HashSet<>();

    @ManyToMany(mappedBy = "reeksen", fetch = FetchType.LAZY)
    private Set<Toernooien> toernooien = new HashSet<>();

    public Integer getReeksID() {
        return reeksID;
    }

    public void setReeksID(Integer reeksID) {
        this.reeksID = reeksID;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
