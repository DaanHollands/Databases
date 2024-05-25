package be.kuleuven.tennistoernooijava.models.SessionHolders;

import be.kuleuven.tennistoernooijava.models.Spelers;

public class SpelerSessie {
    private static SpelerSessie sessie;

    private Spelers speler;

    private SpelerSessie() {
    }

    public static SpelerSessie getSessie() {
        if(sessie == null) {
            sessie = new SpelerSessie();
        }
        return sessie;
    }

    public void setSpeler(Spelers speler) {
        this.speler = speler;
    }

    public void verwijderSessie() {
        speler = null;
    }

    public Spelers getSpeler() {
        return speler;
    }
}
