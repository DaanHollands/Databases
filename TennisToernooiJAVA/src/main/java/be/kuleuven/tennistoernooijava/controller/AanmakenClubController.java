package be.kuleuven.tennistoernooijava.controller;
import be.kuleuven.tennistoernooijava.Exceptions.EmptyInputException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalAdresException;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.service.ChangeScene;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.service.TennisclubService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AanmakenClubController extends BaseController
{
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

    private TennisclubService tennisclubService;
    private SpelerService spelerService;
    @FXML
    void initialize() {
        tennisclubService = new TennisclubService(new TennisclubDAO());
        spelerService = new SpelerService(new SpelersDAO());
        maakClubKnop.setOnMouseClicked(this::maakClub);
    }

    private void maakClub(MouseEvent event) {
        try {
            Tennisclubs tennisclub = tennisclubService.create(SpelerSessie.getSessie().getSpeler(), straatnaamInput.getText(), straatnummerInput.getText(), postcodeInput.getText(), clubNaamInput.getText());
            spelerService.joinClub(tennisclub, SpelerSessie.getSessie().getSpeler());

            try {
                new ChangeScene().switchToScene((Node) event.getSource(), "BekijkClubFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (EmptyInputException | IllegalAdresException | IllegalStateException e) {
            showAlert("Error", e.getMessage());
        }
    }
}
