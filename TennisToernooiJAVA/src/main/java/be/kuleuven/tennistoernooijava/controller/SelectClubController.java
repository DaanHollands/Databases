package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalClubRequest;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.service.TennisclubService;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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


    private SpelerService spelerService;
    private TennisclubService clubService;

    @FXML
    void initialize() {
        spelerService = new SpelerService(new SpelersDAO());
        clubService = new TennisclubService(new TennisclubDAO());

        Tennisclubs club = SpelerSessie.getSessie().getSpeler().getTennisclubID();
        update(club);
        verlaatClubKnop.setOnAction(e -> {
            leaveClub(club);
            update(null);
        });

        clubsList.setOnMouseClicked(event -> {
            clubNameInput.setText(clubsList.getSelectionModel().getSelectedItem());
        });

        joinClubKnop.setOnAction(e -> {
//            huidigeClub.setText(joinClub(clubsList.getSelectionModel().getSelectedItem()));
            Tennisclubs nieuweclub = joinClub(clubsList.getSelectionModel().getSelectedItem());
            update(nieuweclub);
        });

    }

    public void update(Tennisclubs club) {
        if(club != null) {
            huidigeClub.setText(club.getNaam());
        }
        else {
            huidigeClub.setText("Je zit nog niet bij een club");
        }

        clubsList.getItems().clear();
        clubService.getClubNames().forEach(e -> {
            clubsList.getItems().add(e);
        });
    }


    public void leaveClub(Tennisclubs tennisclub) {
        try {
            spelerService.leaveClub(SpelerSessie.getSessie().getSpeler().getSpelerID(), tennisclub);
            huidigeClub.setText("Je zit nog niet bij een club");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Tennisclubs joinClub(String tennisclubnaam) {
        if(!clubService.getClubNames().contains(tennisclubnaam)) {
            throw new IllegalClubRequest("Dit is niet een geldige tennisclub naam");
        }
        else {
            return clubService.addSpelerToClub(
                    SpelerSessie.getSessie().getSpeler(),
                    clubService.getClubFromName(tennisclubnaam)
            );
        }
    }
}
