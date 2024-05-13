package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.ToernooienDAO;
import be.kuleuven.tennistoernooijava.database.Reeksen;
import be.kuleuven.tennistoernooijava.database.Spelers;
import be.kuleuven.tennistoernooijava.database.Tennisclubs;
import be.kuleuven.tennistoernooijava.database.Toernooien;
import be.kuleuven.tennistoernooijava.models.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.ToernooiService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

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
        opslaanKnop.setOnAction(e -> saveToernooi());
        extraReeksKnop.setOnAction(e -> addReeks());
    }

    private void saveToernooi() {
        Toernooien toernooi = service.createToernooi(club,
            beginDatumInput.getValue().getDayOfMonth(), beginDatumInput.getValue().getMonthValue(), beginDatumInput.getValue().getYear(),
            eindDatumInput.getValue().getDayOfMonth(), eindDatumInput.getValue().getMonthValue(), eindDatumInput.getValue().getYear()
        );

        reeksen.forEach(e -> {
            service.addReeks(toernooi, e.getText());
        });
    }

    public void addReeks() {
        reeksen.add(new TextField());
        reeksen.forEach(e -> {
            reeksenVBox.getChildren().add(e);
        });
    }
}
