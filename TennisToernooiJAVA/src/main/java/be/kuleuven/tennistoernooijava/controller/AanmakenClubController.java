package be.kuleuven.tennistoernooijava.controller;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalNumberException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalStreetException;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.service.ChangeScene;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.service.TennisclubService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AanmakenClubController {
    @FXML
    private Button maakClubKnop;

    @FXML
    private TextField clubNaamInput;

    @FXML
    private TextField postcodeInput;

    @FXML
    private TextField straatnaamInput;

    @FXML
    private TextField straatnummerInput;

    private TennisclubService service;
    private SpelerService spelerService;
    @FXML
    void initialize() {
        service = new TennisclubService(new TennisclubDAO());
        spelerService = new SpelerService(new SpelersDAO());
        maakClubKnop.setOnMouseClicked(event -> {
            maakClub();
            try {
                new ChangeScene().switchToScene(maakClubKnop, "ClubFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    void maakClub() {
        try {
            Tennisclubs tennisclub = service.create(SpelerSessie.getSessie().getSpeler(), straatnaamInput.getText(), Integer.parseInt(straatnummerInput.getText()), Integer.parseInt(postcodeInput.getText()), clubNaamInput.getText());
            spelerService.joinClub(tennisclub, SpelerSessie.getSessie().getSpeler());
        }
        catch (IllegalNumberException | IllegalStreetException e) {
            System.out.println(e.getMessage());
        }
    }
}
