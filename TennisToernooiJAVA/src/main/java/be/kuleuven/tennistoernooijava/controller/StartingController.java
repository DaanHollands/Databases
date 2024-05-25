package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.exceptions.SpelerNotFoundException;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.utils.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;

public class StartingController extends BaseController
{
    @FXML
    private Button loginKnop;

    @FXML
    private Button nieuweSpelerKnop;

    @FXML
    private TextField spelerIDInput;

    private SpelerService spelerService;

    private ChangeScene switchScene = new ChangeScene();
    @FXML
    void initialize() {
        spelerService = new SpelerService(new SpelersDAO());
        loginKnop.setOnAction(this::login);

        nieuweSpelerKnop.setOnAction(event -> {
            try {
                switchScene.switchToScene(nieuweSpelerKnop,"AanmakenSpelerFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }}
        );
    }

    private void login(ActionEvent event) {
        try {
            Spelers speler = spelerService.getSpeler(Integer.parseInt(spelerIDInput.getText()));
            try {
                SpelerSessie.getSessie().setSpeler(speler);
                switchScene.switchToScene( (Node)event.getSource(),"DashboardFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (SpelerNotFoundException e) {
            showAlert("Error", e.getMessage());
        }
    }
}
