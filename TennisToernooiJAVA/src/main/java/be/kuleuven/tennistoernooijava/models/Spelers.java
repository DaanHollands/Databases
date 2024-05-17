package be.kuleuven.tennistoernooijava.models;

import be.kuleuven.tennistoernooijava.enums.Geslachten;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spelers")
public class Spelers {
    @Id
    @GeneratedValue(generator = "customid")
    @GenericGenerator(name = "customid", strategy = "be.kuleuven.tennistoernooijava.models.CustomIdGenerator")
    @Column(name = "spelerID", nullable = false, unique = true)
    private Integer spelerID;

    @Column(name = "naam", nullable = false)
    private String naam;

    @Column(name = "telefoonnummer", nullable = false)
    private String telefoonnummer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "datumID",  referencedColumnName = "datumID", nullable = false)
    private Datums datumID;

    @Column(name = "gewicht", nullable = false)
    private Integer gewicht;

    @Column(name = "lengte", nullable = false)
    private Integer lengte;

    @Column(name = "ranking", nullable = false)
    private Integer ranking;

    @Column(name = "geslacht", nullable = false)
    @Enumerated(EnumType.STRING)
    private Geslachten geslacht;

    @OneToMany(mappedBy = "spelerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SpelerEmailadressen> emails = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tennisclubID",  referencedColumnName = "clubID")
    private Tennisclubs tennisclubID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "reeksID", referencedColumnName = "reeksID"),
            @JoinColumn(name = "niveau", referencedColumnName = "niveau")
    })
    private Reeksen reeksID;

    @OneToMany(mappedBy = "deelnameID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Deelnamen> deelnamens = new HashSet<>();

    public Set<SpelerEmailadressen> getEmails() {
        return emails;
    }

    public void removeEmailAdres(SpelerEmailadressen email) {
        for(SpelerEmailadressen e : getEmails()) {
            if(e.equals(email)) {
                emails.remove(e);
                return;
            }
        }
    }

    public void addEmails(SpelerEmailadressen email) {
        emails.add(email);
    }

    public Integer getSpelerID() {
        return spelerID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public Datums getDatumID() {
        return datumID;
    }

    public void setDatumID(Datums datumID) {
        this.datumID = datumID;
    }

    public Integer getGewicht() {
        return gewicht;
    }

    public void setGewicht(Integer gewicht) {
        this.gewicht = gewicht;
    }

    public Integer getLengte() {
        return lengte;
    }

    public void setLengte(Integer lengte) {
        this.lengte = lengte;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Geslachten getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(Geslachten geslacht) {
        this.geslacht = geslacht;
    }

    public Tennisclubs getTennisclubID() {
        return tennisclubID;
    }

    public void setTennisclubID(Tennisclubs tennisclubID) {
        this.tennisclubID = tennisclubID;
    }
}
