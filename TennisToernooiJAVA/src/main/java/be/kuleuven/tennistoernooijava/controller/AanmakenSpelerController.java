package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.Exceptions.IllegalEmailException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalNumberException;
import be.kuleuven.tennistoernooijava.Exceptions.IllegalSexException;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.service.ChangeScene;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class AanmakenSpelerController
{
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button terugKnop;

    @FXML
    private ChoiceBox<Integer> dagInput;

    @FXML
    private TextField emailInput;

    @FXML
    private ChoiceBox<String> geslachtSelector;

    @FXML
    private TextField gewichtInput;

    @FXML
    private TextField jaarInput;

    @FXML
    private TextField lengteInput;

    @FXML
    private ChoiceBox<Integer> maandInput;

    @FXML
    private TextField naamInput;

    @FXML
    private TextField rankingInput;

    @FXML
    private TextField telefoonNummerInput;
    @FXML
    private Button voegToeKnop;

    private String geslachten[] = Arrays.stream(Geslachten.values()).map(Enum::toString).toArray(String[]::new);
    private Integer dagen[] =  IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new);
    private Integer maanden[] =  IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new);
    private ChangeScene switchScene = new ChangeScene();

    private SpelerService service;

    @FXML
    void initialize() {
        service = new SpelerService(new SpelersDAO());
        geslachtSelector.setItems(FXCollections.observableArrayList(geslachten));
        dagInput.setItems(FXCollections.observableArrayList((dagen)));
        maandInput.setItems(FXCollections.observableArrayList((maanden)));
        voegToeKnop.setOnAction(this::maakSpeler);

        terugKnop.setOnAction(event -> {
            try {
                switchScene.switchToScene( (Node)event.getSource(),"StartingFXML");
            } catch (IOException e) {
                System.out.println(e);
            }}
        );
    }

    private void maakSpeler(ActionEvent event) {
        if(geslachtSelector.getValue().isEmpty()){
            throw new IllegalArgumentException("Geen geslacht geselecteerd!");
        }

        Geslachten selectedGeslacht = Geslachten.valueOf(geslachtSelector.getValue());

        try {
            Spelers speler = service.createSpeler(
                    naamInput.getText(), telefoonNummerInput.getText(), dagInput.getValue(),
                    maandInput.getValue(), jaarInput.getText(),
                    Integer.parseInt(gewichtInput.getText()), Integer.parseInt(lengteInput.getText()),
                    Integer.parseInt(rankingInput.getText()), selectedGeslacht, emailInput.getText()
            );
                    SpelerSessie.getSessie().setSpeler(speler);
                    try {
                        switchScene.switchToScene((Node) event.getSource(), "DashboardFXML");
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
        }
        catch (IllegalNumberException | IllegalEmailException | IllegalSexException e){
            System.out.print(e.getMessage());
        }
    }
}
