package be.kuleuven.tennistoernooijava.models;

import be.kuleuven.tennistoernooijava.enums.VeldSoort;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "velden")
public class Velden {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "veldID", nullable = false)
    private Integer veldID;

    @Column(name = "vledsoort", nullable = false)
    @Enumerated(EnumType.STRING)
    private VeldSoort veldsoort;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tennisclubID", referencedColumnName = "clubID", nullable = false)
    private Tennisclubs tennisclubID;

    @OneToMany(mappedBy = "veldID", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Matchen> matchen = new HashSet<>();


    public Integer getVeldID() {
        return veldID;
    }

    public VeldSoort getVeldsoort() {
        return veldsoort;
    }

    public void setVeldsoort(VeldSoort veldsoort) {
        this.veldsoort = veldsoort;
    }

    public Tennisclubs getTennisclubID() {
        return tennisclubID;
    }

    public void setTennisclubID(Tennisclubs tennisclubID) {
        this.tennisclubID = tennisclubID;
    }
}
