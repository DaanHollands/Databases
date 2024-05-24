package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.Exceptions.ClubNotFoundException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalClubException;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.service.TennisclubService;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.Optional;

public class SelectClubController {
    @FXML
    private ListView<String> clubsList;

    @FXML
    private TextField clubNameInput;

    @FXML
    private Text huidigeClub;

    @FXML
    private Button verlaatClubKnop;

    @FXML
    private Button joinClubKnop;

    private TennisclubService clubService;

    @FXML
    void initialize() {
        clubService = new TennisclubService(new TennisclubDAO());

        Tennisclubs club = SpelerSessie.getSessie().getSpeler().getTennisclubID();
        update(club);
        verlaatClubKnop.setOnAction(e -> leaveClub(club));
        clubsList.setOnMouseClicked(event -> {
            clubNameInput.setText(clubsList.getSelectionModel().getSelectedItem());
        });

        joinClubKnop.setOnAction(e -> joinClub(clubsList.getSelectionModel().getSelectedItem()));

    }

    private void update(Tennisclubs club) {
        if(club != null) {
            huidigeClub.setText(club.getNaam());
        } else {
            huidigeClub.setText("Je zit nog niet bij een club");
        }
        clubsList.getItems().clear();
        clubService.getClubNames().forEach(e -> {
            clubsList.getItems().add(e);
        });
    }

    private void leaveClub(Tennisclubs tennisclub) {
        clubService.leaveClub(SpelerSessie.getSessie().getSpeler(), tennisclub);
        huidigeClub.setText("Je zit nog niet bij een club");
        update(null);
    }

    public void joinClub(String tennisclubnaam) {
        try {
            Tennisclubs nieuweclub = clubService.addSpelerToClub(SpelerSessie.getSessie().getSpeler(), tennisclubnaam);
            update(nieuweclub);
        } catch (IllegalClubException | ClubNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
