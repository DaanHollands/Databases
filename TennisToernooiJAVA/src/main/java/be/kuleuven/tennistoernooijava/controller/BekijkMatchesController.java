package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.models.Finales;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.Toernooien;
import be.kuleuven.tennistoernooijava.enums.Posities;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class BekijkMatchesController {
    @FXML
    private ListView<String> toernooienList;

    @FXML
    private ListView<String> matchesList;

    @FXML
    private AnchorPane nietGespeeldAnchorpane;

    @FXML
    private AnchorPane finaleAnchorpane;

    @FXML
    private CheckBox supporterSelector;

    @FXML
    private CheckBox ballenraperSelector;

    @FXML
    private ListView<String> positiesList;

    @FXML
    private Text positiesText;

    @FXML
    private Button opslaanKnop;

    @FXML
    private CheckBox deelnameSelector;

    @FXML
    private AnchorPane gespeeldeAnchorpane;

    @FXML
    private Text thuisScoreText;

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
        positiesList.getItems().setAll(Arrays.toString(Posities.values()));

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
            finaleAnchorpane.setVisible(matchens.get(matchesList.getSelectionModel().getSelectedIndex()) instanceof Finales);
        });

        matchesList.setOnMouseClicked(e -> {
            if(matchens.get(matchesList.getSelectionModel().getSelectedIndex()).getUitslag() == null) {
                nietGespeeldAnchorpane.setVisible(true);
                gespeeldeAnchorpane.setVisible(false);
            }
            else {
                nietGespeeldAnchorpane.setVisible(false);
                gespeeldeAnchorpane.setVisible(true);
                Matchen match = matchens.get(matchesList.getSelectionModel().getSelectedIndex());
                thuisScoreText.setText(match.getScorethus().toString());
                uitScoreText.setText(match.getScoreuit().toString());
                uiteindelijkeScoreText.setText(match.getUitslag().toString());
            }
        });

        deelnameSelector.setOnAction(e -> neemDeel());
        supporterSelector.setOnAction(e -> wordtSupporter());
        ballenraperSelector.setOnAction(e -> wordtBallenraper());

        opslaanKnop.setOnAction(e -> opslaanVoorMatch());
    }

    public void updateMatches(Toernooien toernooi) {
        matchesList.getItems().clear();
        matchens.clear();
        matchens.addAll(toernooi.getMatchen());

        for (Matchen match : matchens) {
            matchesList.getItems().add(matchens.indexOf(match) + " :  " +
                    match.getDatumID().getDag() + "/" + match.getDatumID().getMaand() + "/" + match.getDatumID().getJaar() + "/"
            );
        }
    }

    public void opslaanVoorMatch() {
        if(ballenraperSelector.isSelected()) {
            ballenraperService.createBallenraper(speler, (Finales) matchens.get(matchesList.getSelectionModel().getSelectedIndex()));
        }
        else if(deelnameSelector.isSelected()) {
            deelnameService.createDeelname(speler, (Finales) matchens.get(matchesList.getSelectionModel().getSelectedIndex()));
        }

        else if(supporterSelector.isSelected()) {
            supporterService.createSupporter(speler, (Finales) matchens.get(matchesList.getSelectionModel().getSelectedIndex()), speler.getTennisclubID());
        }
    }

    public void wordtSupporter() {
        ballenraperSelector.setSelected(false);
        deelnameSelector.setSelected(false);
    }

    public void wordtBallenraper() {
        supporterSelector.setSelected(false);
        deelnameSelector.setSelected(false);
    }

    public void neemDeel() {
        supporterSelector.setSelected(false);
        ballenraperSelector.setSelected(false);
    }

}
