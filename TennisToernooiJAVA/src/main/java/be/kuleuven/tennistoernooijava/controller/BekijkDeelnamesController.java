package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.exceptions.IllegalScoreException;
import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.models.Finales;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.enums.Uitslagen;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BekijkDeelnamesController extends BaseController
{
    @FXML
    private ListView<String> matchenList;

    @FXML
    private Text rangText;

    @FXML
    private AnchorPane wedstrijdleiderAnchorpane;

    @FXML
    private TextField scoreThuisInput;

    @FXML
    private TextField scoreUitInput;

    @FXML
    private Button scoresOpslaanKnop;

    @FXML
    private ListView<String> spelerList;

    @FXML
    private ListView<Uitslagen> uitlsagenList;

    private DeelnameService deelnameService;
    private MatchenService matchenService;
    private SupporterService supporterService;
    private BallenraperService ballenraperService;
    private ToernooiService toernooiService;
    private final Spelers speler = SpelerSessie.getSessie().getSpeler();
    private ArrayList<Matchen> matchen = new ArrayList<>();

    private static final String WEDSTRIJDLEIDER = "wedstrijdleider";
    private static final String SPELER  = "speler";
    private static final String SUPPORTER = "supporter";
    private static final String BALLENRAPER = "ballenraper";
    private static final String SCHEIDS = "scheids";
    private static final String NO_MATCH_SELECTED = "Nog geen match geselecteerd!";

    @FXML
    void initialize() {
        initializeServices();
        loadMatchen();
        setupEventHandlers();
        wedstrijdleiderAnchorpane.setVisible(false);
        uitlsagenList.getItems().addAll(Uitslagen.values());
    }

    private void initializeServices() {
        deelnameService = new DeelnameService(new DeelnamenDAO());
        matchenService = new MatchenService(new MatchenDAO());
        supporterService = new SupporterService(new SupporterDAO());
        ballenraperService = new BallenraperService(new BallenraperDAO());
        toernooiService = new ToernooiService(new ToernooienDAO());
    }

    private void loadMatchen() {
        List<Matchen> matchens = matchenService.getMatchesFromEverything(speler);
        matchens.forEach(matchen::add);
        updateMatchenList(matchens);
    }

    private void setupEventHandlers() {
        scoresOpslaanKnop.setOnAction(e -> saveScores());
        matchenList.setOnMouseClicked(e -> handleMatchSelection());
    }

    private void handleMatchSelection() {
        int selectedIndex = matchenList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Matchen selectedMatch = matchen.get(selectedIndex);
            updateSpelerList(selectedMatch);
            scoresOpslaanKnop.setVisible(true);

            if (selectedMatch instanceof Finales) {
                updateForFinale((Finales) selectedMatch);
            } else {
                updateForMatch(selectedMatch);
            }
        } else {
            rangText.setText(NO_MATCH_SELECTED);
            wedstrijdleiderAnchorpane.setVisible(false);
        }
    }

    private void updateForFinale(Finales finale) {
        if (supporterService.isSupporter(finale, speler)) {
            rangText.setText(SUPPORTER);
        } else if (ballenraperService.isBallenraper(finale, speler)) {
            rangText.setText(BALLENRAPER);
        } else if (finale.getScheidsID().getScheids().equals(speler)) {
            rangText.setText(SCHEIDS);
        }
    }

    private void updateForMatch(Matchen match) {
        if (match.getUitslag() != null) {
            scoresOpslaanKnop.setVisible(false);
        }

        if (match.getToernooiID().getWedstrijdleider().getSpeler().equals(speler)) {
            rangText.setText(WEDSTRIJDLEIDER);
            wedstrijdleiderAnchorpane.setVisible(true);
        } else if (deelnameService.isDeelnemer(match, speler)) {
            rangText.setText(SPELER);
        }
    }

    private void updateSpelerList(Matchen match) {
        spelerList.getItems().clear();
        match.getDeelnamens().forEach(d -> spelerList.getItems().add("Deelnemer: " + d.getSpelerID().getNaam()));
    }

    private void updateMatchenList(List<Matchen> matchens) {
        matchenList.getItems().clear();
        for (Matchen match : matchens) {
            matchenList.getItems().add(String.format("%d : %d/%d/%d Reeks: %s %s Ronde: %s",
                    matchens.indexOf(match),
                    match.getDatumID().getDag(),
                    match.getDatumID().getMaand(),
                    match.getDatumID().getJaar(),
                    match.getReeks().getReeks(),
                    match.getReeks().getNiveau(),
                    match.getMatchRonde()
            ));
        }
    }

    private void saveScores() {
        try {
            int selectedIndex = matchenList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Matchen selectedMatch = matchen.get(selectedIndex);
                matchenService.updateScores(
                        selectedMatch,
                        scoreThuisInput.getText(),
                        scoreUitInput.getText(),
                        uitlsagenList.getSelectionModel().getSelectedItem()
                );
                toernooiService.updateMatchen(selectedMatch.getToernooiID());
            }
        } catch (IllegalScoreException e) {
            showAlert("Error", e.getMessage());
        }
    }

}
