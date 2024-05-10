package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.ChangeScene;
import be.kuleuven.tennistoernooijava.models.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;

public class StartingController {
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
        loginKnop.setOnAction(event -> login(event));

        nieuweSpelerKnop.setOnAction(event -> {
            try {
                switchScene.switchToScene( (Node)event.getSource(),"AanmakenSpelerFXML");
            } catch (IOException e) {
                System.out.println(e);
            }}
        );
    }

    private void login(ActionEvent event) {
        if(spelerService.getSpeler(Integer.parseInt(spelerIDInput.getText())) != null) {
            try {
                SpelerSessie.getSessie().setSpeler(spelerService.getSpeler(Integer.parseInt(spelerIDInput.getText())));
                switchScene.switchToScene( (Node)event.getSource(),"DashboardFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
