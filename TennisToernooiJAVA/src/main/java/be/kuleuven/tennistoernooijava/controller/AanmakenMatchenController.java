package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.FinaleService;
import be.kuleuven.tennistoernooijava.service.MatchenService;
import be.kuleuven.tennistoernooijava.service.TennisclubService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class AanmakenMatchenController {
    @FXML
    private ListView<String> toernooiList;

    @FXML
    private DatePicker datumPicker;

    @FXML
    private TextField uurInput;

    @FXML
    private TextField minuutInput;

    @FXML
    private ListView<VeldSoort> veldList;

    @FXML
    private Label scheidsText;

    @FXML
    private CheckBox finaleBoolean;

    @FXML
    private ListView<String> spelerListScheids;

    @FXML
    private Button opslaanKnop;

    private TennisclubService clubService;
    private MatchenService matchenService;
    private FinaleService finaleService;
    private final Spelers speler = SpelerSessie.getSessie().getSpeler();
    private ArrayList<Toernooien> toernooien = new ArrayList<>();
    private ArrayList<Spelers> spelers = new ArrayList<>();
    @FXML
    void initialize() {
        matchenService = new MatchenService(new MatchenDAO());
        finaleService = new FinaleService(new FinaleDAO());
        clubService = new TennisclubService(new TennisclubDAO());
        toernooien.addAll(clubService.getToernooien(speler.getTennisclubID()));
        spelers.addAll(clubService.getAlleSpelers(speler.getTennisclubID()));

        scheidsText.setVisible(false);

        for (Toernooien toernooi : toernooien) {
            toernooiList.getItems().add(toernooien.indexOf(toernooi) + " :  " +
                    toernooi.getBeginDatumID().getDag() + "/" + toernooi.getBeginDatumID().getMaand() + "/" + toernooi.getBeginDatumID().getMaand()
                    + " tot " +
                    toernooi.getEindDatumID().getDag() + "/" + toernooi.getEindDatumID().getMaand() + "/" + toernooi.getEindDatumID().getMaand()
            );
        }
        for (Spelers speler : spelers) {
            spelerListScheids.getItems().add(speler.getNaam());
        }


        veldList.getItems().addAll(VeldSoort.values());
        spelerListScheids.setVisible(false);
        finaleBoolean.setOnAction(event -> {
            spelerListScheids.setVisible(finaleBoolean.isSelected());
            scheidsText.setVisible(finaleBoolean.isSelected());
        });
        opslaanKnop.setOnAction(event -> saveMatch());
    }

    public void saveMatch() {
        if(!finaleBoolean.isSelected()) {
            Matchen match = matchenService.voegMatchAanToernooi(
                    toernooien.get(toernooiList.getSelectionModel().getSelectedIndex()),
                    clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID() ),
                    datumPicker.getValue().getDayOfMonth(),
                    datumPicker.getValue().getMonthValue(),
                    datumPicker.getValue().getYear(),
                    Integer.parseInt(uurInput.getText()),
                    Integer.parseInt(minuutInput.getText()),
                    speler
            );
        }
        else {
            Finales finale = finaleService.voegFinaleAanToernooi(
                    toernooien.get(toernooiList.getSelectionModel().getSelectedIndex()),
                    clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID() ),
                    datumPicker.getValue().getDayOfMonth(),
                    datumPicker.getValue().getMonthValue(),
                    datumPicker.getValue().getYear(),
                    Integer.parseInt(uurInput.getText()),
                    Integer.parseInt(minuutInput.getText()),
                    speler, spelers.get(spelerListScheids.getSelectionModel().getSelectedIndex())
            );
        }
    }
}
