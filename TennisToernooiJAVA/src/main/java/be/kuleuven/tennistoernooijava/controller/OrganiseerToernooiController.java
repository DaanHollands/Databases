package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.ToernooienDAO;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.models.Toernooien;
import be.kuleuven.tennistoernooijava.service.ChangeScene;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.ToernooiService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class OrganiseerToernooiController {
    @FXML
    private DatePicker beginDatumInput;

    @FXML
    private DatePicker eindDatumInput;

    @FXML
    private VBox reeksenVBox;

    @FXML
    private TextField reeksInput1;

    @FXML
    private TextField reeksInput2;

    @FXML
    private TextField reeksInput3;

    @FXML
    private TextField reeksInput4;

    @FXML
    private Button extraReeksKnop;

    @FXML
    private Button opslaanKnop;

    private ToernooiService service;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();
    private Tennisclubs club;

    private ArrayList<TextField> reeksen = new ArrayList<>();
    @FXML
    void initialize() {
        reeksen.add(reeksInput1);reeksen.add(reeksInput2);reeksen.add(reeksInput3);reeksen.add(reeksInput4);
        service = new ToernooiService(new ToernooienDAO());
        club = speler.getTennisclubID();
        opslaanKnop.setOnAction(event -> {
            saveToernooi();
            try {
                new ChangeScene().switchToScene(opslaanKnop, "AanmakenMatchenFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });
        extraReeksKnop.setOnAction(e -> addReeks());
    }

    private void saveToernooi() {
        try{
        Toernooien toernooi = service.createToernooi(club,
            beginDatumInput.getValue().getDayOfMonth(), beginDatumInput.getValue().getMonthValue(), beginDatumInput.getValue().getYear(),
            eindDatumInput.getValue().getDayOfMonth(), eindDatumInput.getValue().getMonthValue(), eindDatumInput.getValue().getYear(),
            speler
        );

        reeksen.forEach(e -> {
            service.addReeks(toernooi, e.getText());
        });
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void addReeks() {
        reeksen.add(new TextField());
        reeksenVBox.getChildren().clear();
        reeksen.forEach(e -> {
            reeksenVBox.getChildren().add(e);
        });
    }
}
