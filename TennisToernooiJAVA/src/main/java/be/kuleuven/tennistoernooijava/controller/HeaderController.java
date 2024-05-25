package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.utils.ChangeScene;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class HeaderController extends BaseController
{
    @FXML
    private MenuBar menuBar;

    private final ChangeScene switchScene = new ChangeScene();

    private Menu home = new Menu("Home");
    private Menu club = new Menu("Club");
    private Menu instellingen = new Menu("Instellingen");

    private MenuItem dashboard = new MenuItem("Dashboard");
    private MenuItem maakClub = new MenuItem("Maak Club");
    private MenuItem selectClubs = new MenuItem("Join club");
    private MenuItem veranderProfiel = new MenuItem("Verander profiel");
    private MenuItem signOut = new MenuItem("Sign out");
    private MenuItem bekijkClub = null;
    private MenuItem organiseerToernooi = null;
    private MenuItem aanmakenMatch = null;
    private MenuItem bekijkDeelnames = null;
    private MenuItem bekijkMatches = null;

    @FXML
    void initialize() {
        setupMenu();
    }


    private void setupMenu() {
        menuBar.getMenus().clear();
        setupHomeMenu();
        setupClubMenu();
        setupInstellingenMenu();
        menuBar.getMenus().addAll(home, club, instellingen);
    }

    private void setupInstellingenMenu() {
        instellingen.getItems().addAll(veranderProfiel, signOut);
        veranderProfiel.setOnAction(event -> switchScene("SpelerSettingsFXML"));
        signOut.setOnAction(event -> {
            SpelerSessie.getSessie().verwijderSessie();
            switchScene("StartingFXML");
        });
    }

    private void setupHomeMenu() {
        home.getItems().add(dashboard);
        dashboard.setOnAction(event -> switchScene("DashboardFXML"));
    }

    private void setupClubMenu() {
        club.getItems().addAll(maakClub, selectClubs);
        maakClub.setOnAction(event -> switchScene("AanmakenClubFXML"));
        selectClubs.setOnAction(event -> switchScene("SelectClubFXML"));

        if (SpelerSessie.getSessie().getSpeler().getTennisclubID() != null) {
            addClubMenuItems();
        }
    }

    private void addClubMenuItems() {
        bekijkClub = new MenuItem("Bekijk club");
        organiseerToernooi = new MenuItem("Organiseer toernooi");
        aanmakenMatch = new MenuItem("Aanmaken match");
        bekijkDeelnames = new MenuItem("Bekijk deelnames");
        bekijkMatches = new MenuItem("Bekijk matches");

        club.getItems().addAll(bekijkClub, organiseerToernooi, aanmakenMatch, bekijkDeelnames, bekijkMatches);

        bekijkClub.setOnAction(event -> switchScene("BekijkClubFXML"));
        organiseerToernooi.setOnAction(event -> switchScene("OrganiseerToernooiFXML"));
        aanmakenMatch.setOnAction(event -> switchScene("AanmakenMatchenFXML"));
        bekijkDeelnames.setOnAction(event -> switchScene("BekijkDeelnamesFXML"));
        bekijkMatches.setOnAction(event -> switchScene("BekijkMatchesFXML"));
    }

    private void switchScene(String fxml) {
        try {
            switchScene.switchToScene(menuBar, fxml);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
