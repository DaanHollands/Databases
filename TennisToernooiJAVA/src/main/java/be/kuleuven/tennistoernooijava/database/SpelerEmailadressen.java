package be.kuleuven.tennistoernooijava.database;

import javax.persistence.*;

@Entity
@Table(name = "emailAdressen")
@IdClass(SpelerEmailadressenPK.class)
public class SpelerEmailadressen {
    @Id
    @ManyToOne
    @JoinColumn(name = "spelerID", referencedColumnName = "spelerID")
    private Spelers spelerID;

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    public Spelers getSpelerID() {
        return spelerID;
    }

    public void setSpelerID(Spelers spelerID) {
        this.spelerID = spelerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
