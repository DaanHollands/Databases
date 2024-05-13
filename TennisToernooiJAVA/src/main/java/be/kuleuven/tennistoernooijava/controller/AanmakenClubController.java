package be.kuleuven.tennistoernooijava.controller;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
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
    private SpelerService spelerService;
    @FXML
    void initialize() {
        try {
            service = new TennisclubService(new TennisclubDAO());
            spelerService = new SpelerService(new SpelersDAO());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        maakClubKnop.setOnMouseClicked(event -> maakClub());
    }

    void maakClub() {
        try {
            Tennisclubs tennisclub = service.create(SpelerSessie.getSessie().getSpeler(), straatnaamInput.getText(), Integer.parseInt(straatnummerInput.getText()), Integer.parseInt(postcodeInput.getText()), clubNaamInput.getText());
            spelerService.joinClub(tennisclub, SpelerSessie.getSessie().getSpeler());
        }
        catch (InputMismatchException e) {
            System.out.println("InputMismatchException");
        }
    }
}
