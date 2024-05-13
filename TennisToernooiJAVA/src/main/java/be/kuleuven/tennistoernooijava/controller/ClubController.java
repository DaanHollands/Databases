package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.Adressen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.models.Toernooien;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.TennisclubService;
import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class ClubController {
    @FXML
    private Text clubnaam;

    @FXML
    private Text clubadres;

    @FXML
    private ListView<String> allespelerList;

    @FXML
    private ListView<String> toernooien;

    private TennisclubService service;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();
    @FXML
    void initialize() {
        service = new TennisclubService(new TennisclubDAO());
        Tennisclubs club = service.getClubFromName(speler.getTennisclubID().getNaam());
        clubnaam.setText(club.getNaam());
        Adressen adres = club.getAdresID();
        clubadres.setText(adres.getStraatnaam() + " " + adres.getStraatnummer() + "  postcode: " + adres.getPostcode());

        club.getSpelers().forEach(speler -> {
            allespelerList.getItems().add(speler.getNaam());
        });

        for (Toernooien toernooi : club.getToernooien()) {
            toernooien.getItems().add(
                    toernooi.getBeginDatumID().getDag() + "/" + toernooi.getBeginDatumID().getMaand() + "/" + toernooi.getBeginDatumID().getMaand()
                    + " tot " +
                    toernooi.getEindDatumID().getDag() + "/" + toernooi.getEindDatumID().getMaand() + "/" + toernooi.getEindDatumID().getMaand()
            );
        }
    }
}
