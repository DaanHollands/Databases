package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.Exceptions.EmptyInputException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalMatchInToernooiException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalTimeException;
import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.*;
import java.util.stream.Collectors;

public class AanmakenMatchenController extends BaseController
{

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

        toernooi = clubService.getToernooiVanSpeler(speler.getTennisclubID(), speler);

        scheidsText.setVisible(false);
        matchSettingAnchorpane.setVisible(false);
        spelerListScheids.setVisible(false);

        spelers.addAll(clubService.getAlleSpelers(speler.getTennisclubID()));
        veldList.getItems().addAll(VeldSoort.values());

        if(MatchenHolderService.getInstance() != null && MatchenHolderService.getInstance().getData() != null) {
            displayMatchenList(MatchenHolderService.getInstance().getData().keySet());
        }
        updateToernooien(toernooi);
        updateSpelers(spelers);

        matchenList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleMatchenListClick();
            }
        });

        okeKnop.setOnAction(event -> saveMatches());


    }

    private void displayMatchenList(Set<Map<Integer, List<Integer>>> matches) {
        matchenList.getItems().clear();
        Map<Integer, List<Integer>> map = matches.iterator().next();
        Integer maxKey = map.keySet().stream().max(Integer::compare).orElse(-1);

        for(Map.Entry<Integer, List<Integer>> match : map.entrySet()) {
            for(Integer aantal : match.getValue()) {
                for(int i = 0; i < aantal; i++) {
                    if(maxKey.equals(match.getKey())) {
                        matchenList.getItems().add("Finale: " + match.getKey() + " -> " + "Match: " + (i+1));
                    } else {
                        matchenList.getItems().add("Ronde: " + match.getKey() + " -> " + "Match: " + (i+1));
                    }
                }
            }
        }
    }

    private void updateToernooien(Toernooien toernooi) {
        if(toernooi != null) {
            toernooiText.setText(
                    toernooi.getBeginDatumID().getDag() + "/" + toernooi.getBeginDatumID().getMaand() + "/" + toernooi.getBeginDatumID().getJaar()
                            + " tot " +
                    toernooi.getEindDatumID().getDag() + "/" + toernooi.getEindDatumID().getMaand() + "/" + toernooi.getEindDatumID().getJaar()
            );
        }
    }

    private void updateSpelers(ArrayList<Spelers> spelers) {
        for (Spelers speler : spelers) {
            spelerListScheids.getItems().add(speler.getNaam());
        }
    }

    private void handleMatchenListClick() {
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

    private void saveMatches() {
        try {
            Map<ReeksenWaardes, Integer> reeksen = new ArrayList<>(MatchenHolderService.getInstance().getData().values()).get(0);
            String selectedItem = matchenList.getSelectionModel().getSelectedItem();
            int matchNumber = Integer.parseInt(selectedItem.split("->")[0].trim().split(":")[1].trim());

            //Voor elke reeks, gaan we een match maken of finale maken
            for(Map.Entry<ReeksenWaardes, Integer> map : reeksen.entrySet()) {
                if(selectedItem.contains("Finale")) {
                    addFinaleToernooi(map, matchNumber);
                } else {
                    addMatchToernooi(map, matchNumber);
                }
            }

            matchenList.getItems().remove(matchenList.getSelectionModel().getSelectedIndex());
            if(matchenList.getItems().isEmpty()) {
                MatchenHolderService.clearInstance();
            }
        }
        catch (IllegalDateException | IllegalTimeException | IllegalMatchInToernooiException | EmptyInputException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void addFinaleToernooi(Map.Entry<ReeksenWaardes, Integer> map, int matchNumber) {
        finaleService.voegFinaleAanToernooi(
                toernooi,
                clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                datumPicker.getValue().getDayOfMonth(),
                datumPicker.getValue().getMonthValue(),
                datumPicker.getValue().getYear(),
                uurInput.getText(),
                minuutInput.getText(),
                spelers.get(spelerListScheids.getSelectionModel().getSelectedIndex()),
                map,
                matchNumber
        );
    }

    private void addMatchToernooi(Map.Entry<ReeksenWaardes, Integer> map, int matchNumber) {
        matchenService.voegMatchAanToernooi(
                toernooi,
                clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                datumPicker.getValue().getDayOfMonth(),
                datumPicker.getValue().getMonthValue(),
                datumPicker.getValue().getYear(),
                uurInput.getText(),
                minuutInput.getText(),
                matchNumber,
                map
        );
    }
}
