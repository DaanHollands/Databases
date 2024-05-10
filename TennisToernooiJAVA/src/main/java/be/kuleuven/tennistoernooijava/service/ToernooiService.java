package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.dao.DatumsDAO;
import be.kuleuven.tennistoernooijava.dao.ToernooienDAO;
import be.kuleuven.tennistoernooijava.database.*;

import java.util.Set;

public class ToernooiService {
    private final ToernooienDAO toernooienDAO;

    public ToernooiService(ToernooienDAO toernooienDAO) {
        this.toernooienDAO = toernooienDAO;
    }

    public Toernooien createToernooi(
            Tennisclubs organisatorClub,
            Integer beginDag, Integer beginMaand, Integer beginJaar,
            Integer eindDag, Integer eindMaand, Integer eindJaar
    ) {
        Toernooien toernooien = new Toernooien();
        Datums beginDatum = new Datums();
        beginDatum.setDag(beginDag);
        beginDatum.setMaand(beginMaand);
        beginDatum.setJaar(beginJaar);

        Datums eindDatum = new Datums();
        eindDatum.setDag(eindDag);
        eindDatum.setMaand(eindMaand);
        eindDatum.setJaar(eindJaar);
        DatumsDAO datumsDAO = new DatumsDAO();
        beginDatum = datumsDAO.create(beginDatum);
        eindDatum = datumsDAO.create(eindDatum);

        toernooien.setBeginDatumID(beginDatum);
        toernooien.setEindDatumID(eindDatum);

        toernooien.setClubOrganistorID(organisatorClub);

        return toernooienDAO.create(toernooien);
    }
}
