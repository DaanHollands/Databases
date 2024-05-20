package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.*;
import be.kuleuven.tennistoernooijava.models.Finales;
import be.kuleuven.tennistoernooijava.models.Matchen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.enums.TypeRang;
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

public class BekijkDeelnamesController {
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
    private Spelers speler = SpelerSessie.getSessie().getSpeler();
    private ArrayList<Matchen> matchen = new ArrayList<>();

    private TypeRang rang = null;

    @FXML
    void initialize() {
        supporterService = new SupporterService(new SupporterDAO());
        ballenraperService = new BallenraperService(new BallenraperDAO());
        matchenService = new MatchenService(new MatchenDAO());
        deelnameService = new DeelnameService(new DeelnamenDAO());
        toernooiService = new ToernooiService(new ToernooienDAO());
        uitlsagenList.getItems().addAll(Uitslagen.values());
        List<Matchen> matchens = matchenService.getMatchesFromEverything(speler);
        matchens.forEach(m -> matchen.add(m));
        wedstrijdleiderAnchorpane.setVisible(false);

        for (Matchen match : matchen) {
            matchenList.getItems().add(matchen.indexOf(match) + " :  " +
                    match.getDatumID().getDag() + "/" + match.getDatumID().getMaand() + "/" + match.getDatumID().getJaar() + "/"
            );
        }

        scoresOpslaanKnop.setOnAction(e -> {
            matchenService.updateScores(matchen.get(matchenList.getSelectionModel().getSelectedIndex()), Integer.parseInt(scoreThuisInput.getText()), Integer.parseInt(scoreUitInput.getText()),
                    uitlsagenList.getSelectionModel().getSelectedItem());
            toernooiService.updateMatchen(matchen.get(matchenList.getSelectionModel().getSelectedIndex()).getToernooiID());

        });

        matchenList.setOnMouseClicked(e -> {
            updateSpelerList(matchen.get(matchenList.getSelectionModel().getSelectedIndex()));
            if(matchen.get(matchenList.getSelectionModel().getSelectedIndex()).getToernooiID().getWedstrijdleider().getSpeler().equals(speler)) {
                rangText.setText("wedstrijdleider");
                rang = TypeRang.WEDSTRIJDLEIDER;
                wedstrijdleiderAnchorpane.setVisible(true);
            }

            else if(deelnameService.isDeelnemer(matchen.get(matchenList.getSelectionModel().getSelectedIndex()), speler)) {
                rangText.setText("speler");
                rang = TypeRang.SPELER;
            }

            else if(matchen.get(matchenList.getSelectionModel().getSelectedIndex()) instanceof Finales) {
                if(supporterService.isSupporter((Finales)matchen.get(matchenList.getSelectionModel().getSelectedIndex()), speler)) {
                    rangText.setText("supporter");
                    rang = TypeRang.SUPPORTER;
                }
                else if(ballenraperService.isBallenraper((Finales)matchen.get(matchenList.getSelectionModel().getSelectedIndex()), speler)) {
                    rangText.setText("ballenraper");
                    rang = TypeRang.BALLENRAPER;
                }
                else if((((Finales) matchen.get(matchenList.getSelectionModel().getSelectedIndex())).getScheidsID().getScheids().equals(speler))) {
                    rangText.setText("scheids");
                    rang = TypeRang.SCHEIDS;
                }
            }

            else {
                rangText.setText("Nog geen match geselecteerd!");
            }
        });

    }
    private void updateSpelerList(Matchen matchen) {
        spelerList.getItems().clear();
        matchen.getDeelnamens().forEach(d -> {
            spelerList.getItems().add(
                    "Deelnemer: " + d.getSpelerID().getNaam()
            );
        });
    }

}
