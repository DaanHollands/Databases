package be.kuleuven.tennistoernooijava.controller;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.service.TennisclubService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.InputMismatchException;

public class AanmakenClubController {
    @FXML
    private Button maakClubKnop;

    @FXML
    private TextField clubNaamInput;

    @FXML
    private TextField postcodeInput;

    @FXML
    private TextField straatnaamInput;

    @FXML
    private TextField straatnummerInput;

    private TennisclubService service;
    @FXML
    void initialize() {
        service = new TennisclubService(new TennisclubDAO());
        maakClubKnop.setOnMouseClicked(event -> maakClub());
    }

    void maakClub() {
        try {
            service.create(straatnaamInput.getText(), Integer.parseInt(straatnummerInput.getText()), Integer.parseInt(postcodeInput.getText()), clubNaamInput.getText());
        }
        catch (InputMismatchException e) {
            System.out.println("InputMismatchException");
        }
    }
}
