package Database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "SpelersEmailadressen", schema = "main", catalog = "")
@javax.persistence.IdClass(Database.dbSpelersEmailadressenPK.class)
public class dbSpelersEmailadressen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "spelerID")
    private int spelerId;

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "Email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbSpelersEmailadressen that = (dbSpelersEmailadressen) o;
        return spelerId == that.spelerId && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelerId, email);
    }
}
