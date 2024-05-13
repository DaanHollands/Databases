package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DashBoardController {
    @FXML
    private Text idField;

    @FXML
    private Text aantalMatchen;

    @FXML
    private Text gewonnenMatchen;

    @FXML
    private Text verlorenMatchen;

    @FXML
    private Text hoogstePlaats;

    @FXML
    private Text huidigeRanking;

    SpelerService service;
    @FXML
    void initialize() {
        service = new SpelerService(new SpelersDAO());
        idField.setText(SpelerSessie.getSessie().getSpeler().getSpelerID().toString());
        huidigeRanking.setText(SpelerSessie.getSessie().getSpeler().getRanking().toString());
    }
}
