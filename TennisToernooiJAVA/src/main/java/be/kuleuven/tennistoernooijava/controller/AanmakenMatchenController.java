package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.exceptions.EmptyInputException;
import be.kuleuven.tennistoernooijava.exceptions.IllegalDateException;
import be.kuleuven.tennistoernooijava.exceptions.IllegalMatchInToernooiException;
import be.kuleuven.tennistoernooijava.exceptions.IllegalTimeException;
import be.kuleuven.tennistoernooijava.models.SessionHolders.MatchenHolderSessie;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.TennisclubDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.VeldSoort;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.*;

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
        initializeServices();
        initializeUIComponents();
        loadToernooiData();
        setupEventHandlers();
    }

    private void initializeServices() {
        matchenService = new MatchenService(new MatchenDAO());
        finaleService = new FinaleService(new FinaleDAO());
        clubService = new TennisclubService(new TennisclubDAO());
    }

    private void initializeUIComponents() {
        scheidsText.setVisible(false);
        matchSettingAnchorpane.setVisible(false);
        spelerListScheids.setVisible(false);
        veldList.getItems().addAll(VeldSoort.values());
    }

    private void loadToernooiData() {
        toernooi = clubService.getToernooiVanSpeler(speler.getTennisclubID(), speler);
        updateToernooien(toernooi);
        spelers.addAll(clubService.getAlleSpelers(speler.getTennisclubID()));
        updateSpelers(spelers);
        if (MatchenHolderSessie.getInstance() != null && MatchenHolderSessie.getInstance().getData() != null) {
            displayMatchenList(MatchenHolderSessie.getInstance().getData().keySet());
        }
    }

    private void setupEventHandlers() {
        matchenList.setOnMouseClicked(event -> handleMatchenListClick());
        okeKnop.setOnAction(event -> saveMatches());
    }

    private void displayMatchenList(Set<Map<Integer, List<Integer>>> matches) {
        matchenList.getItems().clear();
        Map<Integer, List<Integer>> map = matches.iterator().next();
        Integer maxKey = map.keySet().stream().max(Integer::compare).orElse(-1);

        map.forEach((key, values) -> {
            values.forEach(value -> {
                for (int i = 0; i < value; i++) {
                    String matchText = (maxKey.equals(key)) ?
                            "Finale: " + key + " -> Match: " + (i + 1) :
                            "Ronde: " + key + " -> Match: " + (i + 1);
                    matchenList.getItems().add(matchText);
                }
            });
        });
    }

    private void updateToernooien(Toernooien toernooi) {
        if (toernooi != null) {
            toernooiText.setText(
                String.format("%d/%d/%d tot %d/%d/%d",
                        toernooi.getBeginDatumID().getDag(),
                        toernooi.getBeginDatumID().getMaand(),
                        toernooi.getBeginDatumID().getJaar(),
                        toernooi.getEindDatumID().getDag(),
                        toernooi.getEindDatumID().getMaand(),
                        toernooi.getEindDatumID().getJaar()
                )
            );
        }
    }

    private void updateSpelers(ArrayList<Spelers> spelers) {
        spelerListScheids.getItems().clear();
        spelers.forEach(speler -> spelerListScheids.getItems().add(speler.getNaam()));
    }

    private void handleMatchenListClick() {
        String selectedItem = matchenList.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.contains("Match:")) {
            matchSettingAnchorpane.setVisible(true);
            boolean isFinale = selectedItem.contains("Finale");
            scheidsText.setVisible(isFinale);
            spelerListScheids.setVisible(isFinale);
        } else {
            matchSettingAnchorpane.setVisible(false);
        }
    }

    private void saveMatches() {

        try {
            Map<ReeksenWaardes, Integer> reeksen = new ArrayList<>(MatchenHolderSessie.getInstance().getData().values()).get(0);
            String selectedItem = matchenList.getSelectionModel().getSelectedItem();
            int matchNumber = Integer.parseInt(selectedItem.split("->")[0].trim().split(":")[1].trim());

            reeksen.forEach((reeks, reeksniveau) -> {
                if (selectedItem.contains("Finale")) {
                    addFinaleToernooi(reeks, reeksniveau, matchNumber);
                } else {
                    addMatchToernooi(reeks, reeksniveau, matchNumber);
                }
            });

            matchenList.getItems().remove(matchenList.getSelectionModel().getSelectedIndex());
            if (matchenList.getItems().isEmpty()) {
                MatchenHolderSessie.clearInstance();
            }
        } catch (IllegalDateException | IllegalTimeException | IllegalMatchInToernooiException | EmptyInputException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void addFinaleToernooi(ReeksenWaardes reeks, int reeksNiveau, int matchNumber) {
        finaleService.voegFinaleAanToernooi(
                toernooi,
                clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                datumPicker.getValue().getDayOfMonth(),
                datumPicker.getValue().getMonthValue(),
                datumPicker.getValue().getYear(),
                uurInput.getText(),
                minuutInput.getText(),
                spelers.get(spelerListScheids.getSelectionModel().getSelectedIndex()),
                reeks, reeksNiveau,
                matchNumber
        );
    }

    private void addMatchToernooi(ReeksenWaardes reeks, int reeksNiveau, int matchNumber) {
        if(datumPicker.getValue() == null) {
            throw new EmptyInputException("Je moet de datum wel nog invullen eh!");
        }
        matchenService.voegMatchAanToernooi(
                toernooi,
                clubService.getOrCreateVeld(veldList.getSelectionModel().getSelectedItem(), speler.getTennisclubID()),
                datumPicker.getValue().getDayOfMonth(),
                datumPicker.getValue().getMonthValue(),
                datumPicker.getValue().getYear(),
                uurInput.getText(),
                minuutInput.getText(),
                matchNumber,
                reeks, reeksNiveau
        );
    }
}
