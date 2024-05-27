package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.exceptions.*;
import be.kuleuven.tennistoernooijava.models.SessionHolders.MatchenHolderSessie;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.dao.ToernooienDAO;
import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.Tennisclubs;
import be.kuleuven.tennistoernooijava.service.*;
import be.kuleuven.tennistoernooijava.utils.ChangeScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganiseerToernooiController extends BaseController
{
    @FXML
    private DatePicker beginDatumInput;

    @FXML
    private DatePicker eindDatumInput;

    @FXML
    private TextField beginMatchenInput;

    @FXML
    private Button opslaanKnop;

    @FXML
    private TextField reeksNiveauInput;

    @FXML
    private Button reeksToevoegenKnop;

    @FXML
    private ListView<ReeksenWaardes> reeksenList;

    @FXML
    private VBox reeksenVBox;

    private ReeksenService reeksenService;
    private ToernooiService toernooiService;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();
    private Tennisclubs club;

    private Map<ReeksenWaardes, Integer> reeksen = new HashMap<>();

    @FXML
    void initialize() {
        reeksenService = new ReeksenService(null);
        toernooiService = new ToernooiService(new ToernooienDAO());
        club = speler.getTennisclubID();
        reeksenList.getItems().addAll(ReeksenWaardes.values());
        reeksToevoegenKnop.setOnAction(event -> addReeks(reeksenList.getSelectionModel().getSelectedItem(), reeksNiveauInput.getText()));
        opslaanKnop.setOnAction(event -> {
            try {
                saveToernooi();
            } catch (IllegalReeksException | EmptyInputException e) {
                showAlert("Error", e.getMessage());
            }
        });
    }

    private void saveToernooi() {
        if(beginMatchenInput.getText().isEmpty() || beginMatchenInput.getText().contains("[a-zA-Z]+") || Integer.parseInt(beginMatchenInput.getText()) < 4) {
            throw new IllegalReeksException("Je moet minimaal 4 matches hebben!");
        }
        if(reeksen.size() < 4) {
            throw new IllegalReeksException("Je moet minimaal 4 reeksen hebben!");
        }
        if(beginDatumInput.getValue() == null || eindDatumInput.getValue() == null) {
            throw new EmptyInputException("Je bent de dagen van het toernooi vergeten");
        }
        try{
            toernooiService.createToernooi(club,
                beginDatumInput.getValue().getDayOfMonth(), beginDatumInput.getValue().getMonthValue(), beginDatumInput.getValue().getYear(),
                eindDatumInput.getValue().getDayOfMonth(), eindDatumInput.getValue().getMonthValue(), eindDatumInput.getValue().getYear(),
                speler
            );
            Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> map = new HashMap<>();
            map.put(FinaleMatchenHelper.calculateMatches(Integer.parseInt(beginMatchenInput.getText())), reeksen);
            MatchenHolderSessie.getInstance().setData(map);

            try {
                new ChangeScene().switchToScene(opslaanKnop, "AanmakenMatchenFXML");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }catch (IllegalDateException | IllegalWedstrijdleiderException e){
            showAlert("Error", e.getMessage());
        }
    }

    public void addReeks(ReeksenWaardes reeks, String niveau) {
        try {
            reeksenService.validateExceptions(niveau, reeks, reeksen);
            Text text = new Text();
            text.setText(reeks.toString() + " - " + niveau);
            reeksen.put(reeks, Integer.parseInt(niveau));
            reeksenVBox.getChildren().add(text);
        } catch (EmptyInputException | InvalidInputException | IllegalReeksException e) {
            showAlert("Error", e.getMessage());
        }
    }

}
