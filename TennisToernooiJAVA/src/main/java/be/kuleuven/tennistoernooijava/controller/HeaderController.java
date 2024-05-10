package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.models.ChangeScene;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class HeaderController {
    @FXML
    private MenuBar menuBar;

    private Menu home = new Menu("Home");
    private Menu club = new Menu("Club");
    private Menu instellingen = new Menu("Instellingen");

    private MenuItem dashboard = new MenuItem("Dashboard");
    private MenuItem maakClub = new MenuItem("Maak Club");
    private MenuItem selectClubs = new MenuItem("Bekijk Clubs");
    private MenuItem veranderProfiel = new MenuItem("Verander profiel");

    private ChangeScene switchScene = new ChangeScene();
    @FXML
    void initialize() {
        menuBar.getMenus().clear();
        home.getItems().add(dashboard);
        club.getItems().add(maakClub);
        club.getItems().add(selectClubs);
        instellingen.getItems().add(veranderProfiel);
        menuBar.getMenus().addAll(home, club, instellingen);

        veranderProfiel.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene(menuBar,"SpelerSettingsFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        selectClubs.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene( menuBar,"SelectClubFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        maakClub.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene( menuBar,"AanmakenClubFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });
//
//        maakspelerKnop.setOnAction(actionEvent -> {
//            try {
//                switchToScene(actionEvent, "AanmakenSpelerFXML");
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        });

        dashboard.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene( menuBar,"DashboardFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

}
