package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.models.*;
import be.kuleuven.tennistoernooijava.enums.Posities;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BekijkMatchesController {
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
    private MatchenService matchenService;
    private FinaleService finaleService;
    private ToernooiService toernooiService;

    private BallenraperService ballenraperService;
    private SupporterService supporterService;
    private DeelnameService deelnameService;
    private TennisclubService tennisclubService;

    private ArrayList<Matchen> matchens = new ArrayList<>();
    private ArrayList<Toernooien> toernooien = new ArrayList<>();
    private ArrayList<Reeksen> reeksen = new ArrayList<>();

    @FXML
    void initialize() {
        finaleAnchorpane.setVisible(false);
        gespeeldeAnchorpane.setVisible(false);
        nietGespeeldAnchorpane.setVisible(false);

        ballenraperService = new BallenraperService(new BallenraperDAO());
        supporterService = new SupporterService(new SupporterDAO());
        deelnameService = new DeelnameService(new DeelnamenDAO());

        finaleService = new FinaleService(new FinaleDAO());
        tennisclubService = new TennisclubService(new TennisclubDAO());
        toernooiService = new ToernooiService(new ToernooienDAO());
        matchenService = new MatchenService(new MatchenDAO());
        toernooien.addAll(tennisclubService.getToernooien(speler.getTennisclubID()));

        positiesList.getItems().setAll(Posities.values());

        positiesText.setVisible(ballenraperSelector.isSelected());
        positiesList.setVisible(ballenraperSelector.isSelected());

        for (Toernooien toernooi : toernooien) {
            toernooienList.getItems().add(toernooien.indexOf(toernooi) + " :  " +
                    toernooi.getBeginDatumID().getDag() + "/" + toernooi.getBeginDatumID().getMaand() + "/" + toernooi.getBeginDatumID().getMaand()
                    + " tot " +
                    toernooi.getEindDatumID().getDag() + "/" + toernooi.getEindDatumID().getMaand() + "/" + toernooi.getEindDatumID().getMaand()
            );
        }

        toernooienList.setOnMouseClicked(e -> {
            updateMatches(toernooien.get(toernooienList.getSelectionModel().getSelectedIndex()));
        });

        matchesList.setOnMouseClicked(e -> {
            finaleAnchorpane.setVisible(matchens.get(matchesList.getSelectionModel().getSelectedIndex()) instanceof Finales);
            reeksText.setText(matchens.get(matchesList.getSelectionModel().getSelectedIndex()).getReeks().getReeks().toString());
            reeksNiveauText.setText(matchens.get(matchesList.getSelectionModel().getSelectedIndex()).getReeks().getNiveau().toString());
            undoSelectors();

            if(matchens.get(matchesList.getSelectionModel().getSelectedIndex()).getUitslag() == null) {
                nietGespeeldAnchorpane.setVisible(true);
                gespeeldeAnchorpane.setVisible(false);
                updateSpelerList(matchens.get(matchesList.getSelectionModel().getSelectedIndex()));
                deelnemenSelector.setVisible(getDeelnameVisiblity(matchens.get(matchesList.getSelectionModel().getSelectedIndex())));
            }
            else {
                nietGespeeldAnchorpane.setVisible(false);
                gespeeldeAnchorpane.setVisible(true);
                Matchen match = matchens.get(matchesList.getSelectionModel().getSelectedIndex());
                updateSpelerList(match);
                thuisScoreText.setText(match.getScorethus().toString());
                uitScoreText.setText(match.getScoreuit().toString());
                uiteindelijkeScoreText.setText(match.getUitslag().toString());
            }
        });

        deelnemenSelector.setOnAction(e -> neemDeel());
        supporterSelector.setOnAction(e -> wordtSupporter());
        ballenraperSelector.setOnAction(e -> wordtBallenraper());

        opslaanKnop.setOnAction(e -> opslaanVoorMatch());
    }

    private void updateSpelerList(Matchen matchen) {
        spelersList.getItems().clear();
        matchen.getDeelnamens().forEach(d -> {
            spelersList.getItems().add(
                    "Deelnemer: " + d.getSpelerID().getNaam()
            );
        });
    }

    private boolean getDeelnameVisiblity(Matchen match) {
        if(speler.getRanking() > match.getReeks().getNiveau()) {
            return false;
        }
        else if(match.getDeelnamens().size() >= 2) {
            return false;
        }
        else if(!match.getIsFirstMatch()) {
            return false;
        }
        return new ReeksenService(new ReeksenDAO()).canJoinReeks(speler, match.getReeks());
    }

    public void updateMatches(Toernooien toernooi) {
        matchesList.getItems().clear();
        matchens.clear();
        matchens.addAll(toernooi.getMatchen());

        for (Matchen match : matchens) {
            if(match instanceof Finales) {
                matchesList.getItems().add(matchens.indexOf(match) + " Finale " + " :  " +
                        match.getDatumID().getDag() + "/" + match.getDatumID().getMaand() + "/" + match.getDatumID().getJaar()
                );
            }
            else {
                matchesList.getItems().add(matchens.indexOf(match) + " :  " +
                        match.getDatumID().getDag() + "/" + match.getDatumID().getMaand() + "/" + match.getDatumID().getJaar()
                );
            }
        }
    }

    public void opslaanVoorMatch() {
        if(ballenraperSelector.isSelected()) {
            ballenraperService.createBallenraper(speler, (Finales) matchens.get(matchesList.getSelectionModel().getSelectedIndex()));
        }
        else if(deelnemenSelector.isSelected()) {
            deelnameService.createDeelname(speler, matchens.get(matchesList.getSelectionModel().getSelectedIndex()));
        }

        else if(supporterSelector.isSelected()) {
            supporterService.createSupporter(speler, (Finales) matchens.get(matchesList.getSelectionModel().getSelectedIndex()), speler.getTennisclubID());
        }
    }

    public void wordtSupporter() {
        ballenraperSelector.setSelected(false);
        deelnemenSelector.setSelected(false);
    }

    public void wordtBallenraper() {
        supporterSelector.setSelected(false);
        deelnemenSelector.setSelected(false);
    }

    public void neemDeel() {
        supporterSelector.setSelected(false);
        ballenraperSelector.setSelected(false);
    }

    public void undoSelectors() {
        supporterSelector.setSelected(false);
        ballenraperSelector.setSelected(false);
        deelnemenSelector.setSelected(false);
    }

}
