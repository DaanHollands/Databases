package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.FinaleDAO;
import be.kuleuven.tennistoernooijava.dao.MatchenDAO;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.Finales;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.enums.Uitslagen;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.FinaleService;
import be.kuleuven.tennistoernooijava.service.MatchenService;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DashBoardController extends BaseController
{
    @FXML
    private Text idField;

    @FXML
    private Text aantalMatchen;

    @FXML
    private Text gewonnenMatchen;

    @FXML
    private Text verlorenMatchen;

    @FXML
    private Text hoogstePlaats;

    @FXML
    private Text huidigeRanking;

    private SpelerService spelerService;
    private MatchenService matchenService;
    private FinaleService finaleService;
    private Integer gewonnen;
    private Integer verloren;
    private Integer aantalgespeeld;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();
    private int aantalGespeeld;

    @FXML
    void initialize() {
        initializeServices();
        initializeSpelerData();
        updateDashboard();
    }

    private void initializeServices() {
        spelerService = new SpelerService(new SpelersDAO());
        matchenService = new MatchenService(new MatchenDAO());
        finaleService = new FinaleService(new FinaleDAO());
    }

    private void initializeSpelerData() {
        speler = SpelerSessie.getSessie().getSpeler();
        idField.setText(speler.getSpelerID().toString());
        huidigeRanking.setText(speler.getRanking().toString());
    }

    private void updateDashboard() {
        calculateMatchStats();
        updateMatchStatsDisplay();
        updateHoogstePlaats();
    }

    private void calculateMatchStats() {
        gewonnen = 0;
        verloren = 0;
        aantalGespeeld = 0;

        matchenService.getMatchesFrom(speler).forEach(this::updateMatchStats);
        finaleService.getFinalesFrom(speler).forEach(this::updateFinaleStats);

        aantalGespeeld = matchenService.getMatchesFrom(speler).size() + finaleService.getFinalesFrom(speler).size();
    }

    private void updateMatchStats(Matchen match) {
        if (match.getUitslag() == Uitslagen.GEWONNEN) {
            gewonnen++;
        } else if (match.getUitslag() == Uitslagen.VERLOREN) {
            verloren++;
        }
    }

    private void updateFinaleStats(Finales finale) {
        if (finale.getUitslag() == Uitslagen.GEWONNEN) {
            gewonnen++;
        } else if (finale.getUitslag() == Uitslagen.VERLOREN) {
            verloren++;
        }
    }

    private void updateMatchStatsDisplay() {
        aantalMatchen.setText(String.valueOf(aantalGespeeld));
        gewonnenMatchen.setText(String.valueOf(gewonnen));
        verlorenMatchen.setText(String.valueOf(verloren));
    }

    private void updateHoogstePlaats() {
        Matchen hoogsteMatch = matchenService.getHigestMatch(speler);
        Finales hoogsteFinale = finaleService.getHighestFinal(speler);

        if (hoogsteMatch != null && (hoogsteFinale == null || hoogsteFinale.getScorethus() < hoogsteMatch.getScorethus())) {
            hoogstePlaats.setText(formatMatchInfo(hoogsteMatch));
        } else if (hoogsteFinale != null && (hoogsteMatch == null || hoogsteFinale.getScorethus() > hoogsteMatch.getScorethus())) {
            hoogstePlaats.setText(formatFinaleInfo(hoogsteFinale));
        } else {
            hoogstePlaats.setText("Geen matches beschikbaar");
        }
    }

    private String formatMatchInfo(Matchen match) {
        return String.format("%d tegen %d op %d/%d/%d",
                match.getScorethus(),
                match.getScoreuit(),
                match.getDatumID().getDag(),
                match.getDatumID().getMaand(),
                match.getDatumID().getJaar());
    }

    private String formatFinaleInfo(Finales finale) {
        return String.format("%d tegen %d op %d/%d/%d",
                finale.getScorethus(),
                finale.getScoreuit(),
                finale.getDatumID().getDag(),
                finale.getDatumID().getMaand(),
                finale.getDatumID().getJaar());
    }
}
