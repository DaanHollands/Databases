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

    private SpelerService service;
    private MatchenService matchenService;
    private FinaleService finaleService;
    private Integer gewonnen = 0;
    private Integer verloren = 0;
    private Integer aantalgespeeld = 0;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();
    @FXML
    void initialize() {
        matchenService = new MatchenService(new MatchenDAO());
        finaleService = new FinaleService(new FinaleDAO());
        service = new SpelerService(new SpelersDAO());
        idField.setText(SpelerSessie.getSessie().getSpeler().getSpelerID().toString());
        huidigeRanking.setText(SpelerSessie.getSessie().getSpeler().getRanking().toString());

        matchenService.getMatchesFrom(speler).forEach(m -> {
            if(m.getUitslag() == Uitslagen.GEWONNEN) {
                gewonnen++;
            }
            else if(m.getUitslag() == Uitslagen.VERLOREN) {
                verloren++;
            }
        });
        finaleService.getFinalesFrom(speler).forEach(f -> {
            if(f.getUitslag() == Uitslagen.GEWONNEN) {
                gewonnen++;
            }
            else if(f.getUitslag() == Uitslagen.VERLOREN) {
                verloren++;
            }
        });

        verlorenMatchen.setText(verloren.toString());
        gewonnenMatchen.setText(gewonnen.toString());
        aantalgespeeld = finaleService.getFinalesFrom(speler).size() + matchenService.getMatchesFrom(speler).size();
        aantalMatchen.setText(aantalgespeeld.toString());

        Matchen hoogsteMatch = matchenService.getHigestMatch(speler);
        Finales hoogsteFinale = finaleService.getHighestFinal(speler);

        if(hoogsteMatch != null && (hoogsteFinale == null || hoogsteFinale.getScorethus() < hoogsteMatch.getScorethus())) {
            hoogstePlaats.setText(
                    hoogsteMatch.getScorethus() + " tegen " + hoogsteMatch.getScoreuit() + " op " +
                    hoogsteMatch.getDatumID().getDag() + "/" + hoogsteMatch.getDatumID().getMaand() + "/" + hoogsteMatch.getDatumID().getJaar()
            );
        }
        else if(hoogsteFinale != null && (hoogsteMatch == null || hoogsteFinale.getScorethus() > hoogsteMatch.getScorethus())) {
            hoogstePlaats.setText(
                    hoogsteFinale.getScorethus() + " tegen " + hoogsteFinale.getScoreuit() + " op " +
                    hoogsteFinale.getDatumID().getDag() + "/" + hoogsteFinale.getDatumID().getMaand() + "/" + hoogsteFinale.getDatumID().getJaar()
            );
        }

    }
}
