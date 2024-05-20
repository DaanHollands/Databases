package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;
import be.kuleuven.tennistoernooijava.service.*;
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

import java.util.*;

public class AanmakenMatchenController {

    @FXML
    private DatePicker datumPicker;

    @FXML
    private TextField minuutInput;

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
        toernooi = clubService.getToernooiVanSpeler(speler.getTennisclubID(), speler);
        scheidsText.setVisible(false);
        matchSettingAnchorpane.setVisible(false);
        veldList.getItems().addAll(VeldSoort.values());
        spelerListScheids.setVisible(false);

        if(MatchenHolderService.getInstance() != null && MatchenHolderService.getInstance().getData() != null) {
            displayMatchenList(MatchenHolderService.getInstance().getData().keySet());
        }

        if(toernooi != null) {
            toernooiText.setText(
                    toernooi.getBeginDatumID().getDag() + "/" + toernooi.getBeginDatumID().getMaand() + "/" + toernooi.getBeginDatumID().getMaand()
                            + " tot " +
                        toernooi.getEindDatumID().getDag() + "/" + toernooi.getEindDatumID().getMaand() + "/" + toernooi.getEindDatumID().getMaand()
            );
        }

        matchenList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(matchenList.getSelectionModel().getSelectedItem().contains("Match:")) {
                    matchSettingAnchorpane.setVisible(true);
                    if(matchenList.getSelectionModel().getSelectedItem().contains("Finale")) {
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
                Map<ReeksenWaardes, Integer> reeksen = (Map<ReeksenWaardes, Integer>) MatchenHolderService.getInstance().getData().values().toArray()[0];
                if(matchenList.getSelectionModel().getSelectedItem().contains("Finale")) {
                    for(Map.Entry<ReeksenWaardes, Integer> map : reeksen.entrySet()) {
                        finaleService.voegFinaleAanToernooi(
                                toernooi, clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                                datumPicker.getValue().getDayOfMonth(), datumPicker.getValue().getMonthValue(), datumPicker.getValue().getYear(),
                                Integer.parseInt(uurInput.getText()), Integer.parseInt(minuutInput.getText()), spelers.get(spelerListScheids.getSelectionModel().getSelectedIndex()),
                                map, Integer.parseInt(matchenList.getSelectionModel().getSelectedItem().split("->")[0].trim().split(":")[1].trim())
                        );
                    }
                }
                else {
                    for (Map.Entry<ReeksenWaardes, Integer> map : reeksen.entrySet()) {
                        matchenService.voegMatchAanToernooi(
                                toernooi, clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                                datumPicker.getValue().getDayOfMonth(), datumPicker.getValue().getMonthValue(), datumPicker.getValue().getYear(),
                                Integer.parseInt(uurInput.getText()), Integer.parseInt(minuutInput.getText()), Integer.parseInt(matchenList.getSelectionModel().getSelectedItem().split("->")[0].trim().split(":")[1].trim()),
                                map
                        );
                    }
                    }
                matchenList.getItems().remove(matchenList.getSelectionModel().getSelectedIndex());
                if(matchenList.getItems().isEmpty()) {
                    MatchenHolderService.clearInstance();
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        for (Spelers speler : spelers) {
            spelerListScheids.getItems().add(speler.getNaam());
        }

    }

    private void displayMatchenList(Set<Map<Integer, List<Integer>>> matches) {
        matchenList.getItems().clear();
        Map<Integer, List<Integer>> map = matches.iterator().next();
        Optional<Integer> maxKey = map.keySet().stream().max(Integer::compare);

        for(Map.Entry<Integer, List<Integer>> match : map.entrySet()) {
            for(Integer aantal : match.getValue()) {
                for(int i = 0; i < aantal; i++) {
                    if(maxKey.get().equals(match.getKey())) {
                        matchenList.getItems().add("Finale: " + match.getKey() + " -> " + "Match: " + (i+1));
                    } else {
                        matchenList.getItems().add("Ronde: " + match.getKey() + " -> " + "Match: " + (i+1));
                    }
                }
            }
        }
    }
}
