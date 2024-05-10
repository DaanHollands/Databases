package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.service.TennisclubService;

import javafx.fxml.*;
import javafx.scene.control.ListView;

public class SelectClubController {
    @FXML
    private ListView<String> clubsList;

    private SpelerService spelerService;
    private TennisclubService clubService;

    @FXML
    void initialize() {
        clubsList.getItems().addAll(clubService.getClubs().toString());
    }
}
