package be.kuleuven.tennistoernooijava.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HeaderController {
    @FXML
    private Button maakspelerKnop;

    @FXML
    private Button maakclubKnop;

    @FXML
    private Button dashboardKnop;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    void initialize() {
        maakclubKnop.setOnAction(actionEvent -> {
            try {
                switchToScene(actionEvent, "AanmakenClubFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        maakspelerKnop.setOnAction(actionEvent -> {
            try {
                switchToScene(actionEvent, "AanmakenSpelerFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        dashboardKnop.setOnAction(actionEvent -> {
            try {
                switchToScene(actionEvent, "DashboardFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }


    public void switchToScene(ActionEvent event, String sceneNaam) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/be/kuleuven/tennistoernooijava/" + sceneNaam + ".fxml"));
        stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
