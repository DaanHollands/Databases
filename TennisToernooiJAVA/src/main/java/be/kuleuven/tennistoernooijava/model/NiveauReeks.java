package be.kuleuven.tennistoernooijava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class NiveauReeks {
    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "reeksID")
    private Reeksen reeksID;

    public Reeksen getReeksID() {
        return reeksID;
    }

    public void setReeksID(Reeksen reeksId) {
        this.reeksID = reeksId;
    }
    
    @Id
    @Column(name = "niveauID")
    private String niveauID;

    public String getNiveau() {
        return niveauID;
    }

    public void setNiveau(String niveauID) {
        this.niveauID = niveauID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NiveauReeks that = (NiveauReeks) o;
        return reeksID == that.reeksID && Objects.equals(niveauID, that.niveauID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reeksID, niveauID);
    }
}
