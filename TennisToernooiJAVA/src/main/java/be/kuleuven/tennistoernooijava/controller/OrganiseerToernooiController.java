package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalNumberException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalReeksException;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.ToernooienDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.models.Toernooien;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganiseerToernooiController {
    @FXML
    private DatePicker beginDatumInput;

    @FXML
    private DatePicker eindDatumInput;

    @FXML
    private TextField beginMatchenInput;

    @FXML
    private Button opslaanKnop;

    @FXML
    private TextField reeksNiveauInput;

    @FXML
    private Button reeksToevoegenKnop;

    @FXML
    private ListView<ReeksenWaardes> reeksenList;

    @FXML
    private VBox reeksenVBox;


    private ToernooiService service;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();
    private Tennisclubs club;

    private Map<ReeksenWaardes, Integer> reeksen = new HashMap<>();
    @FXML
    void initialize() {
        service = new ToernooiService(new ToernooienDAO());
        club = speler.getTennisclubID();
        reeksenList.getItems().addAll(ReeksenWaardes.values());

        reeksToevoegenKnop.setOnAction(event -> {
            if(reeksNiveauInput.getText().isEmpty()) {
                throw new IllegalReeksException("Je moet eerst een niveau kiezen");
            }
            if(reeksNiveauInput.getText().contains("[a-zA-Z]+")) {
                throw new IllegalNumberException("Je mag enkel nummers ingeven hier");
            }
            if(Integer.parseInt(reeksNiveauInput.getText()) < 0) {
                throw new IllegalNumberException("Het mag geen negatief nummer zijn");
            }
            if(reeksenList.getSelectionModel().getSelectedItem() == null) {
                throw new IllegalReeksException("Je moet eerst een reeks kiezen");
            }

            addReeks(reeksenList.getSelectionModel().getSelectedItem(), Integer.parseInt(reeksNiveauInput.getText()));
        });

        opslaanKnop.setOnAction(event -> {
            saveToernooi();
            try {
                new ChangeScene().switchToScene(opslaanKnop, "AanmakenMatchenFXML");
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    private void saveToernooi() {
        if(reeksen.size() < 4) {
            throw new IllegalReeksException("Je moet minimaal 4 reeksen selecteren!");
        }
        if(Integer.parseInt(beginMatchenInput.getText()) < 4) {
            throw new IllegalReeksException("Je moet minimaal 4 matches hebben!");
        }
        try{
            Toernooien toernooi = service.createToernooi(club,
                beginDatumInput.getValue().getDayOfMonth(), beginDatumInput.getValue().getMonthValue(), beginDatumInput.getValue().getYear(),
                eindDatumInput.getValue().getDayOfMonth(), eindDatumInput.getValue().getMonthValue(), eindDatumInput.getValue().getYear(),
                speler
            );
            Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> map = new HashMap<>();
            map.put(new MatchenService(new MatchenDAO()).calculateMatches(Integer.parseInt(beginMatchenInput.getText())), reeksen);
            MatchenHolderService.getInstance().setData(map);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void addReeks(ReeksenWaardes reeks, Integer niveau) {
        Text text = new Text();
        text.setText(reeks.toString() + " - " + niveau.toString());
        reeksen.put(reeks, niveau);
        reeksenVBox.getChildren().add(text);
    }
}
