package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.Adressen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.models.Toernooien;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.TennisclubService;
import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.Set;

public class BekijkClubController extends BaseController
{
    @FXML
    private Text clubnaam;

    @FXML
    private Text clubadres;

    @FXML
    private ListView<String> allespelerList;

    @FXML
    private ListView<String> toernooien;

    private TennisclubService tennisclubService;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();

    @FXML
    void initialize() {
        tennisclubService = new TennisclubService(new TennisclubDAO());

        Tennisclubs club = tennisclubService.getClubFromName(speler.getTennisclubID().getNaam());
        clubnaam.setText(club.getNaam());

        Adressen adres = club.getAdresID();
        clubadres.setText(adres.getStraatnaam() + " " + adres.getStraatnummer() + "  postcode: " + adres.getPostcode());
        updateSpelers(tennisclubService.getAlleSpelers(club));
        updateToernooien(tennisclubService.getToernooien(club));
    }

    private void updateToernooien(Set<Toernooien> toernooienLijst) {
        for (Toernooien toernooi : toernooienLijst) {
            toernooien.getItems().add(
                    toernooi.getBeginDatumID().getDag() + "/" + toernooi.getBeginDatumID().getMaand() + "/" + toernooi.getBeginDatumID().getMaand()
                            + " tot " +
                    toernooi.getEindDatumID().getDag() + "/" + toernooi.getEindDatumID().getMaand() + "/" + toernooi.getEindDatumID().getMaand()
            );
        }
    }

    private void updateSpelers(Set<Spelers> spelers) {
        spelers.forEach(speler -> {
            allespelerList.getItems().add(speler.getNaam());
        });
    }
}
