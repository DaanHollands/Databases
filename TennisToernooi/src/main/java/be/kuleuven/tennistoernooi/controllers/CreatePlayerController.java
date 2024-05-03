package be.kuleuven.tennistoernooi.controllers;

import be.kuleuven.tennistoernooi.models.CreatePlayer.CreatePlayer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.util.ArrayList;

public class CreatePlayerController {

    @FXML
    private TextField achternaamInput;

    @FXML
    private ChoiceBox<Integer> dagInput;

    @FXML
    private TextField emailInput;

    @FXML
    private ChoiceBox<String> geslachtSelector;

    @FXML
    private TextField gwichtInput;

    @FXML
    private TextField jaarInput;

    @FXML
    private TextField lengteInput;

    @FXML
    private ChoiceBox<?> maandInput;

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
    private CreatePlayer model;
    @FXML
    void initialize() {
        geslachtSelector.setItems(FXCollections.observableArrayList(geslachten));
        dagInput.setItems(FXCollections.observableArrayList((dagen)));
        voegToeKnop.setOnMouseClicked(event -> createPlayer());
        model = new CreatePlayer();
        model.run();
    }


    void createPlayer() {

    }

}