package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.service.ChangeScene;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.view.AanmakenSpelerView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class AanmakenSpelerController
{
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button terugKnop;

    @FXML
    private ChoiceBox<Integer> dagInput;

    @FXML
    private TextField emailInput;

    @FXML
    private ChoiceBox<String> geslachtSelector;

    @FXML
    private TextField gewichtInput;

    @FXML
    private TextField jaarInput;

    @FXML
    private TextField lengteInput;

    @FXML
    private ChoiceBox<Integer> maandInput;

    @FXML
    private TextField naamInput;

    @FXML
    private TextField rankingInput;

    @FXML
    private TextField telefoonNummerInput;
    @FXML
    private Button voegToeKnop;

    private String geslachten[]  = {"man", "vrouw", "in de war"};
    private Integer dagen[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private Integer maanden[] = {1,2,3,4,5,6,7,8,9,10,11,12};
    private ChangeScene switchScene = new ChangeScene();

    private SpelerService service;
    private AanmakenSpelerView view;

    @FXML
    void initialize() {
        service = new SpelerService(new SpelersDAO());
        geslachtSelector.setItems(FXCollections.observableArrayList(geslachten));
        dagInput.setItems(FXCollections.observableArrayList((dagen)));
        maandInput.setItems(FXCollections.observableArrayList((maanden)));
        voegToeKnop.setOnAction(this::maakSpeler);

        terugKnop.setOnAction(event -> {
            try {
                switchScene.switchToScene( (Node)event.getSource(),"StartingFXML");
            } catch (IOException e) {
                System.out.println(e);
            }}
        );
    }

    private void maakSpeler(ActionEvent event) {
        Geslachten selectedGeslacht;
        if(Objects.equals(geslachtSelector.getValue(), "man")) {
            selectedGeslacht = Geslachten.M;
        }
        else if(Objects.equals(geslachtSelector.getValue(), "vrouw")) {
            selectedGeslacht = Geslachten.V;
        }
        else if(Objects.equals(geslachtSelector.getValue(), "in de war")) {
            selectedGeslacht = Geslachten.X;
        }

        else {
            throw new IllegalArgumentException("Geen valid gesalcht is gekozen!");
        }

        Spelers speler = service.createSpeler(
                naamInput.getText(), telefoonNummerInput.getText(), dagInput.getValue(),
                maandInput.getValue(), Integer.parseInt(jaarInput.getText()),
                Integer.parseInt(gewichtInput.getText()), Integer.parseInt(lengteInput.getText()),
                Integer.parseInt(rankingInput.getText()), selectedGeslacht, emailInput.getText()
        );

        if(speler != null) {
            try {
                SpelerSessie.getSessie().setSpeler(speler);
                switchScene.switchToScene( (Node)event.getSource(),"DashboardFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
