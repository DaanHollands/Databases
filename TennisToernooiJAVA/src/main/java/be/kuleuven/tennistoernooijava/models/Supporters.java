package be.kuleuven.tennistoernooijava.models;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clubID", referencedColumnName = "clubID", nullable = false)
    private Tennisclubs clubID;

    @ManyToMany(mappedBy = "supporters" )
    private Set<Finales> finales = new HashSet<>();

    public Spelers getSupporterID() {
        return supporterID;
    }

    public void setSupporterID(Spelers supporterID) {
        this.supporterID = supporterID;
    }

    public Tennisclubs getClubID() {
        return clubID;
    }

    public void setClubID(Tennisclubs clubID) {
        this.clubID = clubID;
    }

    public Set<Finales> getFinales() {
        return finales;
    }

    public void addFinale(Finales finale) {
        finales.add(finale);
    }
}
