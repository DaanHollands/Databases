package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalScoreException;
import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.models.Finales;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.enums.Uitslagen;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        supporterService = new SupporterService(new SupporterDAO());
        ballenraperService = new BallenraperService(new BallenraperDAO());
        matchenService = new MatchenService(new MatchenDAO());
        deelnameService = new DeelnameService(new DeelnamenDAO());
        toernooiService = new ToernooiService(new ToernooienDAO());

        List<Matchen> matchens = matchenService.getMatchesFromEverything(speler);

        uitlsagenList.getItems().addAll(Uitslagen.values());
        matchens.forEach(m -> matchen.add(m));
        updateMatchen(matchens);

        wedstrijdleiderAnchorpane.setVisible(false);

        scoresOpslaanKnop.setOnAction(e -> saveScores());

        matchenList.setOnMouseClicked(e -> clickedOnMatch());

    }

    private void clickedOnMatch() {
        updateSpelerList(matchen.get(matchenList.getSelectionModel().getSelectedIndex()));
        scoresOpslaanKnop.setVisible(true);
        if(matchen.get(matchenList.getSelectionModel().getSelectedIndex()) instanceof Finales) {
            changeForFinale();
        } else if(matchen.get(matchenList.getSelectionModel().getSelectedIndex()) != null) {
            changeForMatch();
        } else {
            rangText.setText(NO_MATCH_SELECTED);
        }
    }

    private void changeForFinale() {
        if(supporterService.isSupporter((Finales)matchen.get(matchenList.getSelectionModel().getSelectedIndex()), speler)) {
            rangText.setText(SUPPORTER);
        } else if(ballenraperService.isBallenraper((Finales)matchen.get(matchenList.getSelectionModel().getSelectedIndex()), speler)) {
            rangText.setText(BALLENRAPER);
        } else if((((Finales) matchen.get(matchenList.getSelectionModel().getSelectedIndex())).getScheidsID().getScheids().equals(speler))) {
            rangText.setText(SCHEIDS);
        }
    }

    private void changeForMatch() {
        if(matchen.get(matchenList.getSelectionModel().getSelectedIndex()).getUitslag() != null) {
            scoresOpslaanKnop.setVisible(false);
        }
        if(matchen.get(matchenList.getSelectionModel().getSelectedIndex()).getToernooiID().getWedstrijdleider().getSpeler().equals(speler)) {
            rangText.setText(WEDSTRIJDLEIDER);
            wedstrijdleiderAnchorpane.setVisible(true);
        } else if(deelnameService.isDeelnemer(matchen.get(matchenList.getSelectionModel().getSelectedIndex()), speler)) {
            rangText.setText(SPELER);
        }
    }

    private void updateSpelerList(Matchen matchen) {
        spelerList.getItems().clear();
        matchen.getDeelnamens().forEach(d -> {
            spelerList.getItems().add(
                    "Deelnemer: " + d.getSpelerID().getNaam()
            );
        });
    }

    private void updateMatchen(List<Matchen> matchen) {
        for (Matchen match : matchen) {
            matchenList.getItems().add(matchen.indexOf(match) + " :  " +
                    match.getDatumID().getDag() + "/" + match.getDatumID().getMaand() + "/" + match.getDatumID().getJaar() + "/" + " Reeks: " + match.getReeks().getReeks() + " " + match.getReeks().getNiveau() +" Ronde: " + match.getMatchRonde()
            );
        }
    }

    private void saveScores() {
        try {
            matchenService.updateScores(
                    matchen.get(matchenList.getSelectionModel().getSelectedIndex()),
                    scoreThuisInput.getText(),
                    scoreUitInput.getText(),
                    uitlsagenList.getSelectionModel().getSelectedItem()
            );
            toernooiService.updateMatchen(matchen.get(matchenList.getSelectionModel().getSelectedIndex()).getToernooiID());
        } catch (IllegalScoreException e) {
            showAlert("Error", e.getMessage());
        }
     }

}
