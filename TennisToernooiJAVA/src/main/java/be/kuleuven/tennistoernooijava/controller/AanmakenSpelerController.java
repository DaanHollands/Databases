package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.exceptions.*;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.utils.ChangeScene;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.stream.IntStream;

public class AanmakenSpelerController extends BaseController
{
    @FXML
    private Button terugKnop;

    @FXML
    private ChoiceBox<Integer> dagInput;

    @FXML
    private TextField emailInput;

    @FXML
    private ChoiceBox<Geslachten> geslachtSelector;

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

    private Integer dagen[] =  IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new);
    private Integer maanden[] =  IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new);
    private ChangeScene switchScene = new ChangeScene();

    private SpelerService service;

    @FXML
    void initialize() {
        service = new SpelerService(new SpelersDAO());
        geslachtSelector.getItems().addAll(Geslachten.values());
        dagInput.setItems(FXCollections.observableArrayList((dagen)));
        maandInput.setItems(FXCollections.observableArrayList((maanden)));
        voegToeKnop.setOnAction(this::maakSpeler);

        terugKnop.setOnAction(event -> {
            try {
                switchScene.switchToScene( (Node)event.getSource(),"StartingFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }}
        );
    }

    private void maakSpeler(ActionEvent event) {
        try {
            Spelers speler = service.createSpeler(
                    naamInput.getText(), telefoonNummerInput.getText(), dagInput.getValue(),
                    maandInput.getValue(), jaarInput.getText(),
                    gewichtInput.getText(), lengteInput.getText(),
                    rankingInput.getText(), geslachtSelector.getValue(), emailInput.getText()
            );
                    SpelerSessie.getSessie().setSpeler(speler);
            try {
                switchScene.switchToScene((Node) event.getSource(), "DashboardFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (InvalidPhoneNumberException | InvalidEmailException | EmptyInputException | IllegalDateException | InvalidInputException e){
            showAlert("Error", e.getMessage());
        }
    }

}
