package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.models.ChangeScene;
import be.kuleuven.tennistoernooijava.models.SpelerSessie;
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
    private MenuItem selectClubs = new MenuItem("Join club");
    private MenuItem veranderProfiel = new MenuItem("Verander profiel");
    private MenuItem bekijkClub = null;
    private MenuItem organiseerToernooi = null;
    private MenuItem aanmakenMatch = null;

    private ChangeScene switchScene = new ChangeScene();
    @FXML
    void initialize() {
        menuBar.getMenus().clear();
        home.getItems().add(dashboard);
        club.getItems().add(maakClub);
        club.getItems().add(selectClubs);

        if (SpelerSessie.getSessie().getSpeler().getTennisclubID() != null) {
            bekijkClub = new MenuItem("Bekijk club");
            organiseerToernooi = new MenuItem("Organiseer toernooi");
            aanmakenMatch = new MenuItem("Aanmaken match");

            club.getItems().add(bekijkClub);
            club.getItems().add(organiseerToernooi);
            club.getItems().add(aanmakenMatch);
        }

        instellingen.getItems().add(veranderProfiel);
        menuBar.getMenus().addAll(home, club, instellingen);

        veranderProfiel.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene(menuBar, "SpelerSettingsFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        selectClubs.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene(menuBar, "SelectClubFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        maakClub.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene(menuBar, "AanmakenClubFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        dashboard.setOnAction(actionEvent -> {
            try {
                switchScene.switchToScene(menuBar, "DashboardFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        if(bekijkClub != null) {
            bekijkClub.setOnAction(actionEvent -> {
                try {
                    switchScene.switchToScene(menuBar, "ClubFXML");
                } catch (IOException e) {
                    System.out.println(e);
                }
            });

            organiseerToernooi.setOnAction(actionEvent -> {
                try {
                    switchScene.switchToScene(menuBar, "OrganiseerToernooiFXML");
                } catch (IOException e) {
                    System.out.println(e);
                }
            });

            aanmakenMatch.setOnAction(actionEvent -> {
                try {
                    switchScene.switchToScene(menuBar, "AanmakenMatchenFXML");
                } catch (IOException e) {
                    System.out.println(e);
                }
            });
        }
    }

}
