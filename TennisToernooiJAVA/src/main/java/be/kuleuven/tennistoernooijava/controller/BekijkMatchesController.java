package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.Posities;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BekijkMatchesController extends BaseController
{
    @FXML
    private CheckBox ballenraperSelector;

    @FXML
    private CheckBox deelnemenSelector;

    @FXML
    private AnchorPane finaleAnchorpane;

    @FXML
    private AnchorPane gespeeldeAnchorpane;

    @FXML
    private ListView<String> matchesList;

    @FXML
    private AnchorPane nietGespeeldAnchorpane;

    @FXML
    private Button opslaanKnop;

    @FXML
    private ListView<Posities> positiesList;

    @FXML
    private Text reeksNiveauText;

    @FXML
    private Text positiesText;

    @FXML
    private Text reeksText;

    @FXML
    private Text scheidsText;

    @FXML
    private ListView<String> spelersList;

    @FXML
    private CheckBox supporterSelector;

    @FXML
    private Text thuisScoreText;

    @FXML
    private ListView<String> toernooienList;

    @FXML
    private Text uitScoreText;

    @FXML
    private Text uiteindelijkeScoreText;


    private final Spelers speler = SpelerSessie.getSessie().getSpeler();
    private BallenraperService ballenraperService;
    private SupporterService supporterService;
    private DeelnameService deelnameService;
    private TennisclubService tennisclubService;

    private ArrayList<Matchen> matchens = new ArrayList<>();
    private ArrayList<Toernooien> toernooien = new ArrayList<>();

    @FXML
    void initialize() {
        initializeServices();
        initializeUIComponents();
        loadToernooien();
        setupEventHandlers();
    }

    private void initializeServices() {
        ballenraperService = new BallenraperService(new BallenraperDAO());
        supporterService = new SupporterService(new SupporterDAO());
        deelnameService = new DeelnameService(new DeelnamenDAO());
        tennisclubService = new TennisclubService(new TennisclubDAO());
    }

    private void initializeUIComponents() {
        finaleAnchorpane.setVisible(false);
        gespeeldeAnchorpane.setVisible(false);
        nietGespeeldAnchorpane.setVisible(false);
        positiesText.setVisible(false);
        positiesList.setVisible(false);
        positiesList.getItems().setAll(Posities.values());
    }

    private void loadToernooien() {
        toernooien.addAll(tennisclubService.getToernooien(speler.getTennisclubID()));
        updateToernooienList(toernooien);
    }

    private void setupEventHandlers() {
        toernooienList.setOnMouseClicked(e -> updateMatches(toernooien.get(toernooienList.getSelectionModel().getSelectedIndex())));
        matchesList.setOnMouseClicked(e -> handleMatchClick());
        deelnemenSelector.setOnAction(e -> selectDeelname());
        supporterSelector.setOnAction(e -> selectSupporter());
        ballenraperSelector.setOnAction(e -> selectBallenraper());
        opslaanKnop.setOnAction(e -> saveMatchParticipation());
    }

    private void handleMatchClick() {
        finaleAnchorpane.setVisible(false);
        Matchen selectedMatch = matchens.get(matchesList.getSelectionModel().getSelectedIndex());

        if (selectedMatch instanceof Finales) {
            displayFinaleDetails((Finales) selectedMatch);
        }

        updateMatchDetails(selectedMatch);
        resetSelectors();

        if (selectedMatch.getUitslag() == null) {
            displayNonPlayedMatchDetails(selectedMatch);
        } else {
            displayPlayedMatchDetails(selectedMatch);
        }
    }

    private void displayPlayedMatchDetails(Matchen match) {
        nietGespeeldAnchorpane.setVisible(false);
        gespeeldeAnchorpane.setVisible(true);
        updatePlayerList(match);
        thuisScoreText.setText(match.getScorethus().toString());
        uitScoreText.setText(match.getScoreuit().toString());
        uiteindelijkeScoreText.setText(match.getUitslag().toString());
    }

    private void displayNonPlayedMatchDetails(Matchen match) {
        nietGespeeldAnchorpane.setVisible(true);
        gespeeldeAnchorpane.setVisible(false);
        updatePlayerList(match);
        deelnemenSelector.setVisible(shouldShowDeelnemenSelector(match));
    }

    private void displayFinaleDetails(Finales finale) {
        finaleAnchorpane.setVisible(true);
        scheidsText.setText(finale.getScheidsID().getScheids().getNaam());
        if (finale.getBallenrapers().stream().anyMatch(b -> b.getSpeler().equals(speler)) ||
                finale.getSupporters().stream().anyMatch(s -> s.getSupporterID().equals(speler))) {
            finaleAnchorpane.setVisible(false);
        }
    }

    private void updateMatchDetails(Matchen match) {
        reeksText.setText(match.getReeks().getReeks().toString());
        reeksNiveauText.setText(match.getReeks().getNiveau().toString());
    }

    private void updateToernooienList(ArrayList<Toernooien> toernooien) {
        toernooienList.getItems().clear();
        toernooien.forEach(toernooi -> {
            String toernooiInfo = String.format("%d : %02d/%02d/%04d tot %02d/%02d/%04d",
                    toernooien.indexOf(toernooi),
                    toernooi.getBeginDatumID().getDag(),
                    toernooi.getBeginDatumID().getMaand(),
                    toernooi.getBeginDatumID().getJaar(),
                    toernooi.getEindDatumID().getDag(),
                    toernooi.getEindDatumID().getMaand(),
                    toernooi.getEindDatumID().getJaar()
            );
            toernooienList.getItems().add(toernooiInfo);
        });
    }

    private void updatePlayerList(Matchen match) {
        spelersList.getItems().clear();
        match.getDeelnamens().forEach(d -> spelersList.getItems().add("Deelnemer: " + d.getSpelerID().getNaam()));
    }

    private boolean shouldShowDeelnemenSelector(Matchen match) {
        return speler.getRanking() <= match.getReeks().getNiveau() &&
                match.getDeelnamens().size() < 2 &&
                match.getMatchRonde() == 1 &&
                match.getToernooiID().getMatchen().stream()
                        .noneMatch(m -> m.getDeelnamens().stream()
                                .anyMatch(d -> d.getSpelerID().equals(speler))) &&
                ReeksenService.canJoinReeks(speler, match.getReeks());
    }

    private void updateMatches(Toernooien toernooi) {
        matchesList.getItems().clear();
        matchens.clear();
        matchens.addAll(toernooi.getMatchen());
        matchens.forEach(match -> {
            String matchInfo = match instanceof Finales ?
                    String.format("%d Finale : %02d/%02d/%04d",
                            matchens.indexOf(match),
                            match.getDatumID().getDag(),
                            match.getDatumID().getMaand(),
                            match.getDatumID().getJaar()) :
                    String.format("%d : %02d/%02d/%04d",
                            matchens.indexOf(match),
                            match.getDatumID().getDag(),
                            match.getDatumID().getMaand(),
                            match.getDatumID().getJaar());
            matchesList.getItems().add(matchInfo);
        });
    }

    private void saveMatchParticipation() {
        Matchen selectedMatch = matchens.get(matchesList.getSelectionModel().getSelectedIndex());
        if (ballenraperSelector.isSelected()) {
            ballenraperService.createBallenraper(speler, (Finales) selectedMatch, positiesList.getSelectionModel().getSelectedItem());
        } else if (deelnemenSelector.isSelected()) {
            deelnameService.createDeelname(speler, selectedMatch);
        } else if (supporterSelector.isSelected()) {
            supporterService.createSupporter(speler, (Finales) selectedMatch, speler.getTennisclubID());
        }
    }

    private void selectSupporter() {
        resetSelectors();
        supporterSelector.setSelected(true);
        positiesList.setVisible(false);
        positiesText.setVisible(false);
    }

    private void selectBallenraper() {
        resetSelectors();
        ballenraperSelector.setSelected(true);
        positiesText.setVisible(true);
        positiesList.setVisible(true);
    }

    private void selectDeelname() {
        resetSelectors();
        deelnemenSelector.setSelected(true);
        positiesList.setVisible(false);
        positiesText.setVisible(false);
    }

    private void resetSelectors() {
        supporterSelector.setSelected(false);
        ballenraperSelector.setSelected(false);
        deelnemenSelector.setSelected(false);
        positiesText.setVisible(false);
        positiesList.setVisible(false);
    }
}
