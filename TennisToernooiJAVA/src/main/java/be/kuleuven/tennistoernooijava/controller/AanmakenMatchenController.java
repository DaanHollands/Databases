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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AanmakenMatchenController {
    @FXML
    private TextField beginMatchenInput;

    @FXML
    private DatePicker datumPicker;

    @FXML
    private TextField minuutInput;

    @FXML
    private Button opslaanKnop;

    @FXML
    private Label scheidsText;

    @FXML
    private ListView<String> spelerListScheids;

    @FXML
    private Text toernooiText;

    @FXML
    private ListView<String> matchenList;

    @FXML
    private AnchorPane matchSettingAnchorpane;

    @FXML
    private TextField uurInput;

    @FXML
    private Button okeKnop;

    @FXML
    private ListView<VeldSoort> veldList;

    private TennisclubService clubService;
    private MatchenService matchenService;
    private FinaleService finaleService;
    private final Spelers speler = SpelerSessie.getSessie().getSpeler();
    private ArrayList<Spelers> spelers = new ArrayList<>();
    private Toernooien toernooi;
    @FXML
    void initialize() {
        matchenService = new MatchenService(new MatchenDAO());
        finaleService = new FinaleService(new FinaleDAO());
        clubService = new TennisclubService(new TennisclubDAO());
        spelers.addAll(clubService.getAlleSpelers(speler.getTennisclubID()));
        toernooi =  clubService.getToernooiVanSpeler(speler.getTennisclubID(), speler);
        scheidsText.setVisible(false);
        matchSettingAnchorpane.setVisible(false);
        veldList.getItems().addAll(VeldSoort.values());
        spelerListScheids.setVisible(false);

        if(toernooi != null) {
            toernooiText.setText(
                    toernooi.getBeginDatumID().getDag() + "/" + toernooi.getBeginDatumID().getMaand() + "/" + toernooi.getBeginDatumID().getMaand()
                            + " tot " +
                        toernooi.getEindDatumID().getDag() + "/" + toernooi.getEindDatumID().getMaand() + "/" + toernooi.getEindDatumID().getMaand()
            );
        }

        beginMatchenInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue)
                {
                    displayMatchenList();
                }
            }
        });

        beginMatchenInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    displayMatchenList();
                }
            }
        });

        matchenList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(matchenList.getSelectionModel().getSelectedItem());
                if(matchenList.getSelectionModel().getSelectedItem().contains("Match:")) {
                    matchSettingAnchorpane.setVisible(true);
                    if(matchenList.getSelectionModel().getSelectedIndex() == matchenList.getItems().size()) {
                        scheidsText.setVisible(true);
                        spelerListScheids.setVisible(true);
                    }
                    else {
                        scheidsText.setVisible(false);
                        spelerListScheids.setVisible(false);
                    }
                }
                else {
                matchSettingAnchorpane.setVisible(false);
                }
            }
        });

        okeKnop.setOnAction(event -> {
            try {
                if(matchenList.getSelectionModel().getSelectedIndex() == matchenList.getItems().size()) {
                    finaleService.voegFinaleAanToernooi(
                            toernooi, clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                            datumPicker.getValue().getDayOfMonth(), datumPicker.getValue().getMonthValue(), datumPicker.getValue().getYear(),
                            Integer.parseInt(uurInput.getText()), Integer.parseInt(minuutInput.getText()), spelers.get(spelerListScheids.getSelectionModel().getSelectedIndex())
                    );
                }
                else {
                    matchenService.voegMatchAanToernooi(
                            toernooi, clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                            datumPicker.getValue().getDayOfMonth(), datumPicker.getValue().getMonthValue(), datumPicker.getValue().getYear(),
                            Integer.parseInt(uurInput.getText()), Integer.parseInt(minuutInput.getText())
                    );
                }
                matchenList.getItems().remove(matchenList.getSelectionModel().getSelectedIndex());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });



        for (Spelers speler : spelers) {
            spelerListScheids.getItems().add(speler.getNaam());
        }

    }

    private void displayMatchenList() {
        Map<String, List<Integer>> matches = matchenService.calculateMatches(beginMatchenInput.getText());
        matchenList.getItems().clear();
        for(Map.Entry<String, List<Integer>> match : matches.entrySet()) {
            for(Integer aantal : match.getValue()) {
                matchenList.getItems().add(match.getKey());
                for(int i = 0; i < aantal; i++) {
                    matchenList.getItems().add("Match: " + (i+1) + " ->");
                }
            }
        }
    }

}
